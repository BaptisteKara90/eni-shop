package fr.eni.ecole.enishop.ui.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.vm.ArticleDetailViewModel
import fr.eni.ecole.eni_shop.vm.SettingsViewModel
import fr.eni.ecole.enishop.ui.common.EniShopTopBar
import fr.eni.ecole.enishop.utils.toFrenchStringFormat

@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    id: Long,
    viewModel: ArticleDetailViewModel = viewModel(
        factory = ArticleDetailViewModel.Factory
    ),
    navController: NavHostController,
    settingsViewModel: SettingsViewModel
) {
    val article by viewModel.article.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadArticle(id)
    }

    Scaffold(
        topBar = { EniShopTopBar(navController = navController, settingsViewModel = settingsViewModel) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ArticleDetail(article = article, articleDetailViewModel = viewModel)
        }
    }
}

@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier, articleDetailViewModel: ArticleDetailViewModel) {

    val checkedFav by articleDetailViewModel.checkfav.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = article.name,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge,
            lineHeight = 1.2.em,
            textAlign = TextAlign.Justify,
            modifier = Modifier.clickable {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/search?q=${article.name}")
                ).also { context.startActivity(it) }
            }
        )
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = article.urlImage,
            contentDescription = article.name,
            modifier = Modifier.size(200.dp)
        )
    }
    Text(
        text = article.description,
        textAlign = TextAlign.Justify
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Prix : ${article.price} €")
        Text(text = "Date de sortie : ${article.date.toFrenchStringFormat()}")
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = checkedFav,
            onCheckedChange = {
                if(it){
                    articleDetailViewModel.addArticle()
                    Toast.makeText(context, "Article ajouté aux favoris", Toast.LENGTH_SHORT).show()
                }else{
                    articleDetailViewModel.deleteArticle()
                    Toast.makeText(context, "Article supprimé des favoris", Toast.LENGTH_SHORT).show()
                }
                articleDetailViewModel.updateCheckBox()
            }
        )
        Text(text = "Favoris ?")
    }
}


