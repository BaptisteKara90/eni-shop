package fr.eni.ecole.eni_shop.repository

import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.ArticleDao
import fr.eni.ecole.eni_shop.dao.DaoFactory
import fr.eni.ecole.eni_shop.dao.DaoType

class ArticleRepository(
    private val articleDaoRoom: ArticleDao
) {
    val articleDaoMemory = DaoFactory.createArticleDao(DaoType.MEMORY)

    fun getArticle(id: Long, daoType: DaoType = DaoType.MEMORY): Article {
        return when(daoType){
          DaoType.MEMORY ->  articleDaoMemory.findById(id)
            else -> articleDaoRoom.findById(id)
        }
    }

    fun getArticles(daoType: DaoType = DaoType.MEMORY): List<Article> {
        return when(daoType){
            DaoType.MEMORY -> articleDaoMemory.findAll()
            else -> articleDaoRoom.findAll()
        }
    }

    fun addArticle (article: Article, daoType: DaoType = DaoType.MEMORY): Long {
       return when(daoType){
           DaoType.MEMORY ->  articleDaoMemory.insert(article)
           else -> articleDaoRoom.insert(article)
       }
    }

    fun deleteArticle (article: Article, daoType: DaoType = DaoType.MEMORY) {
        return when(daoType){
            DaoType.MEMORY ->  articleDaoMemory.deleteAll(article)
            else -> articleDaoRoom.deleteAll(article)
        }
    }
}