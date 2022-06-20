package com.android.jetpacknews.domain.model

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
