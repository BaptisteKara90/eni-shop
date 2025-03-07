package fr.eni.ecole.eni_shop.repository

import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.DaoFactory
import fr.eni.ecole.eni_shop.dao.DaoType

class ArticleRepository {
    val articleDao = DaoFactory.createArticleDao(DaoType.MEMORY)

    fun getArticle(id: Long): Article {
        return articleDao.findById(id)
    }

    fun getArticles(): List<Article> {
        return articleDao.findAll()
    }

    fun addArticle (article: Article): Article {
       return articleDao.insert(article)
    }
}