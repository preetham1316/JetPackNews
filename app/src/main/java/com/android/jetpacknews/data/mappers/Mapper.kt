package com.android.jetpacknews.data.mappers

interface Mapper<I, O> {
    fun map(param: I): O
}