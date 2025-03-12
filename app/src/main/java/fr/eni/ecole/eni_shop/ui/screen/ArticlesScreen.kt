package fr.eni.ecole.eni_shop.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.eni.ecole.eni_shop.vm.ArticleListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import fr.eni.ecole.eni_shop.navigation.ArticleAddDestination
import fr.eni.ecole.eni_shop.vm.SettingsViewModel
import fr.eni.ecole.enishop.ui.common.ArticleList
import fr.eni.ecole.enishop.ui.common.CategoryFilterChip
import fr.eni.ecole.enishop.ui.common.EniShopTopBar


@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory),
    onClickToDetail: (Long) -> Unit,
    navController: NavHostController,
    settingsViewModel: SettingsViewModel
) {

    val articles by viewModel.articles.collectAsState()
    val categories by viewModel.categories.collectAsState()
    var selectedCategory by rememberSaveable { mutableStateOf("") }

    val selectedArticles = if (selectedCategory != "") {
        articles.filter {
            it.category == selectedCategory
        }
    } else {
        articles
    }

    Scaffold(
        topBar = { EniShopTopBar(navController = navController, settingsViewModel = settingsViewModel) },
        floatingActionButton = { ArticlelistFAB(navController = navController) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            CategoryFilterChip(categories, selectedCategory = selectedCategory, onCategoryChange = {
                selectedCategory = it
            })
            ArticleList(articles = selectedArticles, onClickToDetail = onClickToDetail)
        }
    }
}

@Composable
fun ArticlelistFAB(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate(ArticleAddDestination.route) {
                launchSingleTop = true
            }
        },
        shape = CircleShape
    ) {
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = "add button",
            modifier = Modifier.size(50.dp)
        )
    }
}