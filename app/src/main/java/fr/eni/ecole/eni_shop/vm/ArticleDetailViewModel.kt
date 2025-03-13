package fr.eni.ecole.eni_shop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.DaoType
import fr.eni.ecole.eni_shop.repository.ArticleRepository
import fr.eni.ecole.eni_shop.room.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleDetailViewModel(private val articleRepository: ArticleRepository) :
    ViewModel() {

    private val _article = MutableStateFlow<Article>(Article())
    val article: StateFlow<Article> = _article

    private val _checkedFav = MutableStateFlow<Boolean>(false)
    val checkfav : StateFlow<Boolean> = _checkedFav

    fun loadArticle(id: Long) {

        viewModelScope.launch(Dispatchers.IO) {
            val article = articleRepository.getArticle(id, DaoType.ROOM)
            if(article != null)
            _checkedFav.value = true
        }

        _article.value = articleRepository.getArticle(id)
    }

    fun addArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.addArticle(article.value, DaoType.ROOM)
        }
    }

    fun deleteArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            articleRepository.deleteArticle(article.value, DaoType.ROOM)
        }
    }

    fun updateCheckBox(){
        _checkedFav.value = !_checkedFav.value
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

                return ArticleDetailViewModel(
                    ArticleRepository( AppDataBase.getInstance(application.applicationContext).articleDao())
                ) as T
            }
        }
    }
}