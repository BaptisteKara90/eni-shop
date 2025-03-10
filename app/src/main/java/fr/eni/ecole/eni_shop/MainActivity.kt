package fr.eni.ecole.eni_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import fr.eni.ecole.eni_shop.ui.theme.EnishopTheme
import fr.eni.ecole.enishop.ui.screen.ArticleDetailScreen


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnishopTheme{
                ArticleDetailScreen(modifier = Modifier, id = 1)
            }
        }
    }
}