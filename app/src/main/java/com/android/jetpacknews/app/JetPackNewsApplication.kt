package com.android.jetpacknews.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetPackNewsApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}
