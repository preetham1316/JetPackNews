package com.android.jetpacknews.di.coroutine

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DispatcherProviderImpl : DispatcherProvider {
    override val main: CoroutineContext by lazy { Dispatchers.Main }
    override val io: CoroutineContext by lazy { Dispatchers.IO }
    override val default: CoroutineContext by lazy { Dispatchers.Default }
}