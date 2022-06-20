package com.android.jetpacknews.domain.repository

import com.android.jetpacknews.domain.model.ArticleResponse

interface ArticleNetworkRepository {

    suspend fun getArticles(query: String): Result<ArticleResponse>
}