package app.bale.newsapplication.data.repository

import app.bale.newsapplication.data.local.ArticleDao
import app.bale.newsapplication.data.model.Articles
import app.bale.newsapplication.data.model.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(var retrofitService: RetrofitService, var articleDao: ArticleDao) {

    suspend fun getAllTopHeadLines(): NewsResponse = retrofitService.getAllTopHeadLines()

    fun bookmarkArticle(article: Articles) {
        articleDao.insertArticle(article)
    }

    suspend fun getAllBookmarkedArticles(): List<Articles> {
        return articleDao.getArticles()
    }

}