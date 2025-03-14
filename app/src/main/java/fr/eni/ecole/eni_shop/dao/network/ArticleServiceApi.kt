package fr.eni.ecole.eni_shop.dao.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.eni.ecole.eni_shop.bo.Article
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleServiceApi {

    @GET("products")
    suspend fun getArticles(): List<Article>

    @GET("products/{id}")
    suspend fun getArticleById(@Path("id")id: Long): Article

    @GET("products/categories")
    suspend fun getCategories(): List<String>



    companion object{
        var BASE_URL = "https://fakestoreapi.com/"

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()
    }


    object ArticleAPI{
        val retrofitService : ArticleServiceApi by lazy { retrofit.create(ArticleServiceApi::class.java) }
    }
}