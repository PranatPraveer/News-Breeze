package com.example.newsapplication.api

import com.example.newsapplication.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/v2/top-headlines")
    suspend fun getNews( @Query("sources") sources : String?, @Query("apiKey") key : String) : Response<News>

}