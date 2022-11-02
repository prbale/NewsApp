package app.bale.newsapplication.domain.usecase

import app.bale.newsapplication.data.model.NewsResponse
import app.bale.newsapplication.domain.repository.NewsRepository
import javax.inject.Inject

class TopHeadLinesUsecase @Inject constructor(private val repository: NewsRepository)  {

    suspend fun getAllTopHeadLines() : NewsResponse {
        val data = repository.getAllTopHeadLines()
        return data
    }

}