package fr.eni.ecole.eni_shop.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.ecole.eni_shop.vm.ArticleListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.eni.ecole.enishop.ui.common.ArticleList
import fr.eni.ecole.enishop.ui.common.CategoryFilterChip
import fr.eni.ecole.enishop.ui.common.EniShopTopBar


@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory)
) {

    val articles by viewModel.articles.collectAsState()
    val categories by viewModel.categories.collectAsState()
    var selectedCategory by rememberSaveable { mutableStateOf<String>("") }

    val selectedArticles = if(selectedCategory != ""){
        articles.filter {
            it.category == selectedCategory
    }
    }else{
        articles
    }

    Scaffold(
        topBar = { EniShopTopBar() }
    ) {
        Column(modifier = Modifier.padding(it)) {
            CategoryFilterChip(categories, selectedCategory = selectedCategory, onCategoryChange = {
                selectedCategory = it
            })
            ArticleList(articles = selectedArticles)
        }
    }
}

@Preview
@Composable
fun preview() {
    ArticlesScreen()
}