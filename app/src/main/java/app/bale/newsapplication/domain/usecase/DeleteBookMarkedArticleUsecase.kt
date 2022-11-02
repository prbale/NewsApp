    package app.bale.newsapplication.domain.usecase

import app.bale.newsapplication.data.model.Article
import app.bale.newsapplication.domain.repository.NewsRepository
import javax.inject.Inject

class DeleteBookMarkedArticleUsecase @Inject constructor(private val repository: NewsRepository)  {

    suspend fun deleteBookMarkedArticle(article: Article) : Unit {
        repository.deleteBookmarkedArticle(article = article )
    }

}