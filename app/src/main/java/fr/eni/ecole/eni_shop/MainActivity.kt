package fr.eni.ecole.eni_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.repository.ArticleRepository
import fr.eni.ecole.eni_shop.ui.screen.ArticleDetailScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticleDetailScreen()
        }
    }
}

//@Composable
//fun ArticleDetailScreen(article: Article?) {
//    Scaffold {
//        Column (verticalArrangement = Arrangement.spacedBy(20.dp) ,modifier = Modifier.fillMaxWidth(),  horizontalAlignment = Alignment.CenterHorizontally){
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 30.dp),
//            ) {
//                Icon(
//                    imageVector = Icons.Default.ShoppingCart,
//                    contentDescription = "a shopping cart",
//                    modifier = Modifier.size(50.dp)
//                )
//                Text(
//                    text = "ENI-SHOP",
//                    modifier = Modifier.padding(it),
//                    style = TextStyle(fontSize = 50.sp),
//                    color = Color(0xFF8E536D)
//                )
//            }
//            Text(
//                text = article?.name ?: "Pas d'article",
//                modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                style = TextStyle(fontSize = 25.sp),
//            )
//            AsyncImage(
//                model = article?.urlImage,
//                contentDescription = article?.name,
//                contentScale = ContentScale.Crop,
//            )
//            Text(
//                text = article?.description ?: "Pas de description",
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
//                textAlign = TextAlign.Center,
//                style = TextStyle(fontSize = 20.sp),
//            )
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//            ){
//                Text(text = "Prix : ${article?.price}€", style = TextStyle(fontSize = 16.sp))
//                Text(
//                    text = "Date de sortie : " + formatDate(article?.date),
//                    style = TextStyle(fontSize = 16.sp)
//                )
//            }
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ){
//                Checkbox(checked = false, onCheckedChange = {})
//                Text(
//                    text = "Favoris",
//                    style = TextStyle(fontSize = 16.sp)
//                )
//            }
//        }
//    }
//}
//
//fun formatDate(date: Date?): String {
//    return if (date != null) {
//        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Format: 29/02/2024
//        sdf.format(date)
//    } else {
//        "Date inconnue"
//    }
//}
//
//@Composable
//@Preview
//fun Preview(){
//    val article = ArticleRepository.getArticle(1)
//    ArticleDetailScreen(article)
//}

//        val article = ArticleRepository.getArticle(1)
//        ArticleRepository.addArticle(Article(category = "test", description = "test", id = 2, name = "test", price = 1.0, urlImage = "test", date = Date()))
//        val article2 = ArticleRepository.getArticle(2)
//        Log.i(TAG, article.toString())
//        Log.i(TAG, article2.toString())

