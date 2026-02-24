package com.ibadetapp.ui.state

/**
 * Sealed class representing UI states for data loading
 * Helps manage loading, success, and error states in UI
 */
sealed class UiState<out T> {
    /**
     * Data is being loaded
     */
    object Loading : UiState<Nothing>()

    /**
     * Data loaded successfully
     * @param data The loaded data
     */
    data class Success<T>(val data: T) : UiState<T>()

    /**
     * An error occurred while loading data
     * @param message Error message to display to user
     * @param exception Optional exception for logging
     */
    data class Error(val message: String, val exception: Exception? = null) : UiState<Nothing>()

    /**
     * Empty state - no data available
     * @param message Message to display (e.g., "No bookmarks yet")
     */
    data class Empty(val message: String = "Veri bulunamadı") : UiState<Nothing>()

    val isLoading: Boolean get() = this is Loading
    val isSuccess: Boolean get() = this is Success<*>
    val isError: Boolean get() = this is Error
    val isEmpty: Boolean get() = this is Empty

    inline fun <R> map(transform: (T) -> R): UiState<R> = when (this) {
        is Success -> Success(transform(data))
        is Loading -> Loading
        is Error -> Error(message, exception)
        is Empty -> Empty(message)
    }
}
