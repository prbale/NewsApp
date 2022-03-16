package app.bale.newsapplication.data.repository

import app.bale.newsapplication.data.model.NewsResponse
import javax.inject.Inject

class NewsRepository @Inject constructor(var retrofitService: RetrofitService) {

    suspend fun getAllTopHeadLines(): NewsResponse = retrofitService.getAllTopHeadLines()
}