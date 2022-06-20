package com.android.jetpacknews.domain.usecase

import com.android.jetpacknews.domain.model.ArticleResponse
import com.android.jetpacknews.domain.repository.ArticleNetworkRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articleNetworkRepository: ArticleNetworkRepository) {

    suspend fun invoke(param: String): Result<ArticleResponse> =
        articleNetworkRepository.getArticles(param)
}