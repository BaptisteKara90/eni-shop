package fr.eni.ecole.eni_shop.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.eni.ecole.eni_shop.bo.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article WHERE id = :id")
    fun findById(id: Long) : Article

    @Insert
    fun insert(article: Article) :Long

    @Query("SELECT * FROM article")
    fun findAll(): List<Article>

    @Delete
    fun deleteAll(article : Article)

}