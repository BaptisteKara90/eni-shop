package fr.eni.ecole.eni_shop.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.eni_shop.dao.ArticleDao
import fr.eni.ecole.eni_shop.utils.DateRoomConverter


@Database(entities = [Article::class], version = 1)
@TypeConverters(DateRoomConverter::class)
abstract class AppDataBase : RoomDatabase()  {

    abstract fun articleDao(): ArticleDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            var instance = INSTANCE

            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "EniShop.db"
                ).fallbackToDestructiveMigrationFrom().build()
                INSTANCE = instance
            }
            return instance
        }
    }
}