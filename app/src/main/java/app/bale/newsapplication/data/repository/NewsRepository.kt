package app.bale.newsapplication.data.repository

import app.bale.newsapplication.data.local.ArticleDao
import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.model.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsRepository
    @Inject constructor(
        var retrofitService: NewsAPIService,
        var articleDao: ArticleDao) {

    suspend fun getAllTopHeadLines(page: Int = 1): Response<NewsResponse> {
        return retrofitService.getAllTopHeadLines(page)
    }

    fun bookmarkArticle(article: Article) {
        articleDao.insertArticle(article)
    }

    fun deleteBookmarkedArticle(article: Article) {
        articleDao.deleteArticle(article)
    }

    fun getAllBookmarkedArticles(): List<Article> {
        return articleDao.getArticles()
    }

    fun removeBookmarkedArticle(article: Article) {
        getAllBookmarkedArticles().forEach { articleValue ->
            if(article.title == articleValue.title) {
                deleteBookmarkedArticle(articleValue)
            }
        }
    }
}