package fr.eni.ecole.eni_shop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.repository.ArticleRepository
import fr.eni.ecole.eni_shop.ui.theme.EnishopTheme
import java.util.Date

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val article = ArticleRepository.getArticle(1)
        ArticleRepository.addArticle(Article(category = "test", description = "test", id = 2, name = "test", price = 1.0, urlImage = "test", date = Date()))
        val article2 = ArticleRepository.getArticle(2)
        Log.i(TAG, article.toString())
        Log.i(TAG, article2.toString())
    }
}

