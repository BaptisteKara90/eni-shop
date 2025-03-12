package fr.eni.ecole.eni_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.ecole.eni_shop.navigation.NavHost
import fr.eni.ecole.eni_shop.ui.theme.EnishopTheme
import fr.eni.ecole.eni_shop.vm.SettingsViewModel
import fr.eni.ecole.enishop.ui.screen.ArticleDetailScreen


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)
            val isDarkTheme by settingsViewModel.isDarkTheme.collectAsState()


            EnishopTheme(darkTheme = isDarkTheme){
                NavHost(settingsViewModel = settingsViewModel)
            }
        }
    }
}