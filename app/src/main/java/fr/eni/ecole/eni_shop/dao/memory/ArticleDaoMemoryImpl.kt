package fr.eni.ecole.eni_shop.dao.memory

import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.ArticleDao
import java.util.Date

class ArticleDaoMemoryImpl : ArticleDao {
    var articles = mutableListOf(
        Article(
            1,
            "Black Philip",
            "La représentation de Satan en personne",
            666.66,
            "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/563361eb-7139-4122-b3af-9353c53bbcfc/dg182kt-854e7620-d5a6-471f-8f1a-2f9a3d8e4977.jpg",
            "animal démoniaque",
            Date()
        )
    )


    override fun findById(id: Long) : Article? {
       return articles.find { it.id == id }
    }

    override fun insert(article: Article) : Article {
        article.id = (articles.size + 1).toLong()
        articles.add(article)
        return article
    }
}
