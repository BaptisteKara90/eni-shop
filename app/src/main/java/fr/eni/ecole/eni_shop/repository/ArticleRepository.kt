package fr.eni.ecole.eni_shop.repository

import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.ArticleDao
import fr.eni.ecole.eni_shop.dao.DaoFactory
import fr.eni.ecole.eni_shop.dao.DaoType
import fr.eni.ecole.eni_shop.dao.network.ArticleServiceApi

class ArticleRepository(
    private val articleDaoRoom: ArticleDao,
    private val articleAPI: ArticleServiceApi
) {


    suspend fun getArticle(id: Long, daoType: DaoType = DaoType.NETWORK): Article {
        return when (daoType) {
            DaoType.NETWORK -> articleAPI.getArticleById(id)
            else -> articleDaoRoom.findById(id)
        }
    }

    suspend fun getArticles(daoType: DaoType = DaoType.MEMORY): List<Article> {
        return when (daoType) {
            DaoType.NETWORK -> articleAPI.getArticles()
            else -> articleDaoRoom.findAll()
        }
    }

    fun addArticle(article: Article, daoType: DaoType = DaoType.MEMORY): Long {
        return when (daoType) {
            DaoType.NETWORK -> TODO()
            else -> articleDaoRoom.insert(article)
        }
    }

    fun deleteArticle(article: Article, daoType: DaoType = DaoType.MEMORY) {
        return when (daoType) {
            DaoType.NETWORK -> TODO()
            else -> articleDaoRoom.deleteAll(article)
        }
    }

    suspend fun getCategories(): List<String> {
        return articleAPI.getCategories()
    }
}