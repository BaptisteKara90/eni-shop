package fr.eni.ecole.eni_shop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.network.ArticleServiceApi
import fr.eni.ecole.eni_shop.repository.ArticleRepository
import fr.eni.ecole.eni_shop.room.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleListViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories


    var isLoading = MutableStateFlow(true)
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val articles = async {_articles.value = articleRepository.getArticles() }
            val categories = async { _categories.value = articleRepository.getCategories()}
            awaitAll(articles, categories)
            isLoading.value = false
        }
    }


    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val savedStateHandle = extras.createSavedStateHandle()

                return ArticleListViewModel(
                    ArticleRepository(AppDataBase.getInstance(application.applicationContext).articleDao(),
                        ArticleServiceApi.ArticleAPI.retrofitService
                        )
                ) as T
            }
        }
    }
}