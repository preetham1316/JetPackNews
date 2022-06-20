package com.android.jetpacknews.domain.datasource

import com.android.jetpacknews.data.model.ArticleResponseDto

interface ArticleNetworkDataSource {
    suspend fun getArticles(query: String): Result<ArticleResponseDto>
}