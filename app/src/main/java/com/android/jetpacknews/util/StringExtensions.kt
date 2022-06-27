package com.android.jetpacknews.util

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.parseDate(): String {
    return try {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val output = SimpleDateFormat("EEE, MMM dd yyyy")
        val parseDate = input.parse(this)
        parseDate?.let { output.format(it) } ?: String.EMPTY
    } catch (e: ParseException) {
        String.EMPTY
    }
}

val String.Companion.EMPTY: String
    get() = ""