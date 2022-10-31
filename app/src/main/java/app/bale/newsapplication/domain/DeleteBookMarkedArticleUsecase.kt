    package app.bale.newsapplication.domain

import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.data.repository.NewsRepository
import javax.inject.Inject

class DeleteBookMarkedArticleUsecase @Inject constructor(private val repository: NewsRepository)  {

    suspend fun deleteBookMakedArticle(article: Article) : Unit {
        repository.deleteBookmarkedArticle(article = article )
    }

}