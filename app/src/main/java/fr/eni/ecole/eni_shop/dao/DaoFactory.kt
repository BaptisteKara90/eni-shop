package fr.eni.ecole.eni_shop.dao

import fr.eni.ecole.eni_shop.dao.memory.ArticleDaoMemoryImpl
import fr.eni.ecole.eni_shop.dao.network.ArticleDaoNetworkImpl

abstract class DaoFactory {
    companion object {
        fun createArticleDao(type: DaoType): ArticleDao {
            return when (type){
                DaoType.MEMORY -> ArticleDaoMemoryImpl()
                DaoType.NETWORK -> ArticleDaoMemoryImpl()
            }
        }
    }
}
