package com.android.jetpacknews.data.mappers

import com.android.jetpacknews.data.model.ArticleResponseDto
import com.android.jetpacknews.domain.model.ArticleResponse
import javax.inject.Inject

class ArticleResponseDtoMapper @Inject constructor(private val articleDtoMapper: ArticleDtoMapper) :
    Mapper<ArticleResponseDto, ArticleResponse> {
    override fun map(param: ArticleResponseDto): ArticleResponse {
        return with(param) {
            ArticleResponse(
                status.orEmpty(),
                totalResults?.toInt() ?: 0,
                articles?.map { articleDtoMapper.map(it) } ?: emptyList()
            )
        }
    }
}