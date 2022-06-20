package com.android.jetpacknews.data.model

import com.google.gson.annotations.SerializedName

data class ArticleResponseDto(
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Long?,
    @SerializedName("articles")
    val articles: List<ArticleDto>?
)