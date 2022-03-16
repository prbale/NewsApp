package app.bale.newsapplication.data.repository

import app.bale.newsapplication.data.model.NewsResponse
import retrofit2.http.GET

interface RetrofitService {

    @GET("top-headlines")
    suspend fun getAllTopHeadLines() : NewsResponse
}