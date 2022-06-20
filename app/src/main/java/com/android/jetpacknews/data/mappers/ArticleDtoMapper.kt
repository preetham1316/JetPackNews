package com.android.jetpacknews.data.mappers

import com.android.jetpacknews.data.model.ArticleDto
import com.android.jetpacknews.domain.model.Article
import javax.inject.Inject

class ArticleDtoMapper @Inject constructor() : Mapper<ArticleDto, Article> {
    override fun map(param: ArticleDto): Article {
        return with(param) {
            Article(
                author.orEmpty(),
                title.orEmpty(),
                description.orEmpty(),
                url.orEmpty(),
                urlToImage.orEmpty(),
                publishedAt.orEmpty(),
                content.orEmpty()
            )
        }
    }
}