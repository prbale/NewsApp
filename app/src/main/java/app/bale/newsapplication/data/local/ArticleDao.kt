package app.bale.newsapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.bale.newsapplication.data.model.Article

@Dao
interface ArticleDao {

    @Query("Select * from article")
    fun getArticles(): List<Article>

    @Insert
    fun insertArticle(article: Article)

    @Delete
    fun deleteArticle(article: Article)

}
