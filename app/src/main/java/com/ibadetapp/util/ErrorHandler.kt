package com.ibadetapp.util

import com.ibadetapp.R

/**
 * Centralized error handling for the app
 */
sealed class AppError(val messageResId: Int) {
    object NetworkError : AppError(R.string.error_network)
    object DatabaseError : AppError(R.string.error_database)
    object FileError : AppError(R.string.error_file)
    object ValidationError : AppError(R.string.error_validation)
    object UnknownError : AppError(R.string.error_unknown)
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val error: AppError) : UiState<Nothing>()
}

object ErrorHandler {
    fun handleException(exception: Exception): AppError {
        return when (exception) {
            is java.io.IOException -> AppError.NetworkError
            is android.database.SQLException -> AppError.DatabaseError
            is java.io.FileNotFoundException -> AppError.FileError
            is IllegalArgumentException -> AppError.ValidationError
            else -> AppError.UnknownError
        }
    }
}
