package fr.eni.ecole.eni_shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.eni.ecole.eni_shop.ui.screen.ArticleFormScreen
import fr.eni.ecole.eni_shop.ui.screen.ArticlesScreen
import fr.eni.ecole.eni_shop.vm.SettingsViewModel
import fr.eni.ecole.enishop.ui.screen.ArticleDetailScreen

@Composable
fun NavHost(navHostController: NavHostController = rememberNavController(), settingsViewModel: SettingsViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = HomeDestination.route
    ) {
        composable(HomeDestination.route) {
            ArticlesScreen( onClickToDetail = { navHostController.navigate("${ArticleDetailDestination.route}/$it")}, navController = navHostController, settingsViewModel = settingsViewModel)
        }
        composable(
            route = ArticleDetailDestination.routeWithArgs,
            arguments = ArticleDetailDestination.args
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(ArticleDetailDestination.argName)
            if (id != null) {
                ArticleDetailScreen(id = id, navController = navHostController, settingsViewModel = settingsViewModel)
            }
        }
        composable(route = ArticleAddDestination.route){
            ArticleFormScreen(navController = navHostController, settingsViewModel = settingsViewModel)
        }
    }
}