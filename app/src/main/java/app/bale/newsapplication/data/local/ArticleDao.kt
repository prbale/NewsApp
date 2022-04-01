package app.bale.newsapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import app.bale.newsapplication.data.model.Articles

@Dao
interface ArticleDao {

    @Query("Select * from article")
    fun getArticles(): List<Articles>

    @Insert
    fun insertArticle(articles: Articles)

    @Delete
    fun deleteArticle(articles: Articles)

}
