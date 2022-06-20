package com.android.jetpacknews.data.repository

import com.android.jetpacknews.data.mappers.ArticleResponseDtoMapper
import com.android.jetpacknews.di.coroutine.DispatcherProvider
import com.android.jetpacknews.domain.datasource.ArticleNetworkDataSource
import com.android.jetpacknews.domain.model.ArticleResponse
import com.android.jetpacknews.domain.repository.ArticleNetworkRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleNetworkRepositoryImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val articleNetworkDataSource: ArticleNetworkDataSource,
    private val articleResponseDtoMapper: ArticleResponseDtoMapper
) : ArticleNetworkRepository {
    override suspend fun getArticles(query: String): Result<ArticleResponse> {
        return withContext(dispatcherProvider.io) {
            articleNetworkDataSource.getArticles(query).map { articleResponseDtoMapper.map(it) }
        }
    }
}