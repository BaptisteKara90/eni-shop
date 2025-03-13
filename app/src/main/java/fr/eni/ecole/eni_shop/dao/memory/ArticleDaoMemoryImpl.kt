package fr.eni.ecole.eni_shop.dao.memory

import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.ArticleDao
import java.util.Date

class ArticleDaoMemoryImpl : ArticleDao {
    var articles = mutableListOf(
        Article(
            id = 1,
            name = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            price = 109.95,
            urlImage = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            category = "men's clothing",
            date = Date(),
        ),
        Article(
            id = 2,
            name = "Mens Casual Premium Slim Fit T-Shirts",
            description = "Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.",
            price = 22.3,
            urlImage = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
            category = "men's clothing",
            date = Date(),
        ),
        Article(
            id = 3,
            name = "Mens Cotton Jacket",
            description = "great outerwear jackets for Spring/Autumn/Winter, suitablefor many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
            price = 55.99,
            urlImage = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
            category = "men's clothing",
            date = Date(),
        )
    )


    override fun findById(id: Long): Article {
        return articles.first { it.id == id }
    }

    override fun insert(article: Article): Long {
        article.id = (articles.size + 1).toLong()
        articles.add(article)
        return article.id
    }

    override fun findAll(): List<Article> {
        return articles
    }

    override fun deleteAll(article: Article) {
        TODO("Not yet implemented")
    }


}
