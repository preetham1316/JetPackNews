/*
 * AuthorizationInterceptor.kt
 * Module: COTO.common.layers.network.main
 * Project: COTO
 * Copyright © 2022, Eve World Platform PTE LTD. All rights reserved.
 */

package com.android.jetpacknews.di.network

import com.android.jetpacknews.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        val original = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", BuildConfig.API_KEY)
            .build()
        return chain.proceed(original.newBuilder().url(url).build())
    }
}
