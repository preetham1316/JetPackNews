package com.android.jetpacknews.di.coroutine

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Provides
    fun provideDispatcher(): DispatcherProvider = DispatcherProviderImpl()
}