package com.ibadetapp.analytics

import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Analytics event tracking system
 * Logs user actions and app events for analysis
 */
object Analytics {

    private const val TAG = "IbadetAnalytics"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    /**
     * Event types
     */
    enum class EventType {
        // App Events
        APP_OPENED,
        APP_CLOSED,

        // Quran Events
        QURAN_VIEWED,
        SURAH_OPENED,
        AYAH_COPIED,
        BOOKMARK_ADDED,
        BOOKMARK_REMOVED,
        FONT_SIZE_CHANGED,

        // Zikir Events
        ZIKIR_STARTED,
        ZIKIR_COMPLETED,
        ZIKIR_ADDED,
        ZIKIR_DELETED,
        ZIKIR_RESET,

        // Settings Events
        SETTINGS_OPENED,
        THEME_CHANGED,
        VIBRATION_TOGGLED,

        // Search Events
        SEARCH_PERFORMED,

        // Filter Events
        FILTER_APPLIED,

        // Navigation Events
        NAVIGATION_SWITCHED,

        // Statistics Events
        STATISTICS_VIEWED
    }

    /**
     * Logs an analytics event
     * @param context Application context
     * @param eventType Type of event
     * @param params Optional event parameters
     */
    fun logEvent(context: Context, eventType: EventType, params: Map<String, String> = emptyMap()) {
        val timestamp = dateFormat.format(Date())
        val paramsStr = if (params.isNotEmpty()) {
            params.entries.joinToString(", ") { "${it.key}=${it.value}" }
        } else {
            "none"
        }

        Log.d(TAG, "Event: $eventType | Time: $timestamp | Params: $paramsStr")

        // TODO: Send to analytics backend (Firebase, Mixpanel, etc.)
    }

    /**
     * Logs custom event
     * @param context Application context
     * @param eventName Event name
     * @param params Event parameters
     */
    fun logCustomEvent(context: Context, eventName: String, params: Map<String, String> = emptyMap()) {
        val timestamp = dateFormat.format(Date())
        val paramsStr = if (params.isNotEmpty()) {
            params.entries.joinToString(", ") { "${it.key}=${it.value}" }
        } else {
            "none"
        }

        Log.d(TAG, "CustomEvent: $eventName | Time: $timestamp | Params: $paramsStr")
    }

    /**
     * Logs screen view
     * @param context Application context
     * @param screenName Screen name
     */
    fun logScreenView(context: Context, screenName: String) {
        Log.d(TAG, "ScreenView: $screenName | Time: ${dateFormat.format(Date())}")
    }

    /**
     * Logs error event
     * @param context Application context
     * @param errorMessage Error message
     * @param exception Exception object (optional)
     */
    fun logError(context: Context, errorMessage: String, exception: Exception? = null) {
        Log.e(TAG, "Error: $errorMessage", exception)
    }

    /**
     * Logs user action duration
     * @param context Application context
     * @param actionName Action name
     * @param durationMs Duration in milliseconds
     */
    fun logActionDuration(context: Context, actionName: String, durationMs: Long) {
        Log.d(TAG, "ActionDuration: $actionName | Duration: ${durationMs}ms")
    }

    /**
     * Gets analytics summary
     * @return Summary string
     */
    fun getAnalyticsSummary(): String {
        return buildString {
            append("Analytics Engine v1.0\n")
            append("- Event logging enabled\n")
            append("- Screen tracking enabled\n")
            append("- Error reporting enabled\n")
            append("- Performance monitoring enabled")
        }
    }
}
