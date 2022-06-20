package com.android.jetpacknews.di.news

import com.android.jetpacknews.data.datasource.ArticleNetworkDataSourceImpl
import com.android.jetpacknews.data.repository.ArticleNetworkRepositoryImpl
import com.android.jetpacknews.domain.datasource.ArticleNetworkDataSource
import com.android.jetpacknews.domain.repository.ArticleNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class HomeDiModule {

    @Binds
    abstract fun provideNetworkDataSource(
        articleNetworkDataSourceImpl: ArticleNetworkDataSourceImpl
    ): ArticleNetworkDataSource

    @Binds
    abstract fun provideNetworkRepository(
        articleNetworkRepositoryImpl: ArticleNetworkRepositoryImpl
    ): ArticleNetworkRepository
}