package fr.eni.ecole.enishop.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.vm.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopTopBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    settingsViewModel: SettingsViewModel
) {
    TopAppBar(title = { EniShopTopBarTitle(settingsViewModel = settingsViewModel) },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Composable
fun EniShopTopBarTitle(modifier: Modifier = Modifier, settingsViewModel: SettingsViewModel) {

    val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()
    val isSwitchVisible = rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "ShoppingCart",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "ENI-SHOP",
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 40.sp
            )
        }
        IconButton(
            onClick = { isSwitchVisible.value = !isSwitchVisible.value },
            modifier = Modifier
        ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
        }
        if (isSwitchVisible.value) {
            Box() {
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { settingsViewModel.toggleDarkTheme() }
                )
            }

        }
    }
}


@Composable
fun EniShopTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    placeholder: @Composable () -> Unit = {},

    ) {

    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = label, fontSize = 20.sp)
            TextField(
                placeholder = placeholder,
                enabled = enabled,
                trailingIcon = trailingIcon,
                modifier = modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange
            )
        }
    }


}


@Composable
fun CategoryFilterChip(
    categories: List<String>,
    selectedCategory: String = "",
    onCategoryChange: (String) -> Unit = {}
) {


    LazyRow {
        items(categories) { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = {
                    if (selectedCategory == category) {
                        onCategoryChange("")
                    } else {
                        onCategoryChange(category)
                    }
                },
                label = { Text(text = category) },
                leadingIcon = if (selectedCategory == category) {
                    { Icon(imageVector = Icons.Default.Done, contentDescription = "Done") }
                } else {
                    null
                })
        }
    }
}

@Composable
fun ArticleList(articles: List<Article>, onClickToDetail: (Long) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(articles) {
            ArticleItem(article = it, onClickToDetail = onClickToDetail)
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ArticleItem(article: Article, modifier: Modifier = Modifier, onClickToDetail: (Long) -> Unit) {
    Card(modifier = Modifier.clickable { onClickToDetail(article.id) }) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(8.dp)
            )
            Text(text = article.name)
            Text(text = "${String.format("%.2f", article.price)} €")
        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        CircularProgressIndicator()
    }
}
