package fr.eni.ecole.eni_shop.ui.screen

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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.repository.ArticleRepository
import fr.eni.ecole.eni_shop.ui.common.EniShopTopBar
import fr.eni.ecole.eni_shop.utils.toFrenchFormat

@Composable
fun ArticleDetailScreen(){
    Scaffold(
        topBar = { EniShopTopBar() }
    ) {
       Column (modifier = Modifier.padding(it)) {
           ArticleDetail(ArticleRepository.getArticle(1))
       }
    }
}

@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier){

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = article.name,
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge,
            lineHeight = 1.em,
            textAlign = TextAlign.Center
        )
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainer,
            modifier = Modifier.fillMaxWidth()
        ){
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
            Text(text = "Prix : ${article.price}€")
            Text(text = "Date de sortie : ${article.date.toFrenchFormat()}")
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = false, onCheckedChange = {})
            Text(text = "Favoris")
        }
    }

}