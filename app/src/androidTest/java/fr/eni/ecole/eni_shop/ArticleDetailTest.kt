package fr.eni.ecole.eni_shop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import fr.eni.ecole.eni_shop.bo.Article
import fr.eni.ecole.enishop.ui.screen.ArticleDetail
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ArticleDetailTest {

    @get: Rule
    val composeTestRule = createComposeRule()


    @Test
    fun articleDetail_show_OK(){
        //article a tester
        val articletest = Article(
            id= 1,
            name="articleTest",
            description= "descriptionTest",
            price= 10.0,
            urlImage= "urlImageTest",
            category= "categoryTest",
            date= Date()
        )

        //charger le composant

        composeTestRule.setContent (){
            Surface(modifier = Modifier.fillMaxSize()){
                ArticleDetail(article = articletest)
            }
        }

        composeTestRule.onNodeWithText("articleTest").assertIsDisplayed()
        composeTestRule.onNodeWithText("descriptionTest").assertIsDisplayed()
        composeTestRule.onNodeWithText("Prix : 10.0 €").assertIsDisplayed()
    }
}