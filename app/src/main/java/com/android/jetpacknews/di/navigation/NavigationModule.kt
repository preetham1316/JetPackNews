package com.android.jetpacknews.di.navigation

import com.android.jetpacknews.navigation.CustomScreenNavigator
import com.android.jetpacknews.navigation.ScreenNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Singleton
    @Provides
    fun providesEvaNavigator(): ScreenNavigator = CustomScreenNavigator()
}