package app.bale.newsapplication.data.repository

import app.bale.newsapplication.data.model.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("top-headlines?sortBy=publishedAt")
    suspend fun getAllTopHeadLines(@Query("page") page: Int): Response<NewsResponse>

}