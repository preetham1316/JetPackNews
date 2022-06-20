package com.android.jetpacknews.data.model

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("content")
    val content: String?
)