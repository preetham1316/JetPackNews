package com.android.jetpacknews.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

@Suppress("TooGenericExceptionCaught", "SwallowedException")
suspend fun <T : Any> safeApiCall(
    call: suspend () -> Response<T>,
    errorHandler: (t: Throwable) -> Throwable
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response: Response<T> = call()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(ErrorState.Undefined)
            }
        } catch (ioexception: IOException) {
            Result.failure(ErrorState.NoInternetException)
        } catch (exception: Exception) {
            Result.failure(ErrorState.CallError(errorHandler(exception)))
        }
    }
}

sealed class ErrorState : Throwable() {
    object NoInternetException : ErrorState()
    object Undefined : ErrorState()
    data class CallError(val error: Throwable) : ErrorState()
}