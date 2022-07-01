package com.android.jetpacknews.data.datasource

import com.android.jetpacknews.coroutine.safeApiCall
import com.android.jetpacknews.data.api.NewsApi
import com.android.jetpacknews.data.model.ArticleResponseDto
import com.android.jetpacknews.domain.datasource.ArticleNetworkDataSource
import javax.inject.Inject

class ArticleNetworkDataSourceImpl @Inject constructor(private val newsApi: NewsApi) :
    ArticleNetworkDataSource {

    override suspend fun getArticles(query: String): Result<ArticleResponseDto> {
        return safeApiCall({ newsApi.getArticles(query = query) }, { Exception(it) })
    }

    override suspend fun searchArticles(query: String): Result<ArticleResponseDto> {
        return safeApiCall({ newsApi.searchArticles(query = query) }, { Exception(it) })
    }
}
