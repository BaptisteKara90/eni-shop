package fr.eni.ecole.eni_shop.bo

import java.util.Date

data class Article(var id: Long, var name: String, var description: String, var price: Double, var urlImage: String, var category: String, var date: Date) {
    override fun toString(): String {
        return "Article(id=$id, name='$name', description='$description', price=$price€, urlImage='$urlImage', category='$category', date=$date)"
    }
}