package fr.eni.ecole.eni_shop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleDetailViewModel(private val articleRepository: ArticleRepository) :
    ViewModel() {

    private val _article = MutableStateFlow<Article>(Article())
    val article: StateFlow<Article> = _article

    fun loadArticle(id: Long) {
        _article.value = articleRepository.getArticle(id)
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                return ArticleDetailViewModel(
                    ArticleRepository()
                ) as T
            }
        }
    }
}