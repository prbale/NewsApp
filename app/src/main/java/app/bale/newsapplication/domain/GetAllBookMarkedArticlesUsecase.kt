package app.bale.newsapplication.domain

import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.data.repository.NewsRepository
import javax.inject.Inject

class GetAllBookMarkedArticlesUsecase @Inject constructor(private val repository: NewsRepository)  {

    suspend fun getAllBookMarkedArticlesUsecase() : List<Article> {
        val data = repository.getAllBookmarkedArticles()
        return data
    }

}