package fr.eni.ecole.eni_shop.bo

import java.util.Date

data class Article(
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    var price: Double = 0.0,
    var urlImage: String = "",
    var category: String = "",
    var date: Date = Date()
) {
    override fun toString(): String {
        return "Article(id=$id, name='$name', description='$description', price=$price€, urlImage='$urlImage', category='$category', date=$date)"
    }
}