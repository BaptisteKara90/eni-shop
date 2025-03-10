package fr.eni.ecole.enishop.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import coil.compose.AsyncImage
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.vm.ArticleDetailViewModel
import fr.eni.ecole.enishop.ui.common.EniShopTopBar
import fr.eni.ecole.enishop.utils.toFrenchStringFormat

@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    id: Long,
    viewModel: ArticleDetailViewModel = viewModel(
        factory = ArticleDetailViewModel.Factory
    )
) {
    val article by viewModel.article.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadArticle(id)
    }

    Scaffold(
        topBar = { EniShopTopBar() }
    ) {
        Column(modifier = Modifier.padding(it)) {
            ArticleDetail(article = article)
        }
    }
}

@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(onClick = { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${article.name}")).also{context.startActivity(it)} }) {
            Text(
                text = article.name,
                fontSize = 30.sp,
                style = MaterialTheme.typography.titleLarge,
                lineHeight = 1.2.em,
                textAlign = TextAlign.Justify
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
            Checkbox(checked = true, onCheckedChange = {})
            Text(text = "Favoris ?")
        }
    }

}

