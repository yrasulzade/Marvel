package com.example.core.util

import android.content.Context
import com.example.core.R
import retrofit2.HttpException
import java.io.IOException

class ErrorHandler(private val context: Context) {
    @Throws(IOException::class)
    fun handleError(throwable: Throwable): String {
        throwable.printStackTrace()

        when (throwable) {
            is HttpException -> {
                val errorMessage = throwable.response()?.message()

                errorMessage?.let {
                    return it
                }
            }
        }

        return context.getString(R.string.general_error)
    }
}
