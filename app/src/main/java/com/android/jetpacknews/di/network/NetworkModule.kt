package com.android.jetpacknews.di.network

import com.android.jetpacknews.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Reusable
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    @Provides
    internal fun provideGson(): Gson = GsonBuilder().create()
}