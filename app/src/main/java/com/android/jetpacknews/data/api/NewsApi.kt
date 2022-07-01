package com.android.jetpacknews.data.api

import com.android.jetpacknews.data.model.ArticleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/everything")
    suspend fun getArticles(@Query("q") query: String): Response<ArticleResponseDto>

    @GET("/v2/top-headlines")
    suspend fun searchArticles(@Query("q") query: String): Response<ArticleResponseDto>
}
