package com.ibadetapp.util

import android.content.Context
import androidx.preference.PreferenceManager

/**
 * Centralized preferences management utility
 * Provides easy access to user settings stored in SharedPreferences
 */
object PreferencesManager {

    private const val PREF_VIBRATION_ENABLED = "vibration_enabled"
    private const val PREF_QURAN_FONT_SIZE = "quran_font_size"
    private const val PREF_APP_THEME = "app_theme"

    /**
     * Gets whether vibration is enabled for zikir counter
     * @param context Application context
     * @return true if vibration is enabled, false otherwise
     */
    fun isVibrationEnabled(context: Context): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(PREF_VIBRATION_ENABLED, true)
    }

    /**
     * Sets vibration preference
     * @param context Application context
     * @param enabled true to enable vibration
     */
    fun setVibrationEnabled(context: Context, enabled: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putBoolean(PREF_VIBRATION_ENABLED, enabled)
            .apply()
    }

    /**
     * Gets the Quran font size preference
     * @param context Application context
     * @return font size in SP (default: 24)
     */
    fun getQuranFontSize(context: Context): Float {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_QURAN_FONT_SIZE, "24")
            ?.toFloatOrNull() ?: 24f
    }

    /**
     * Sets Quran font size preference
     * @param context Application context
     * @param size font size in SP
     */
    fun setQuranFontSize(context: Context, size: Float) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(PREF_QURAN_FONT_SIZE, size.toInt().toString())
            .apply()
    }

    /**
     * Gets the app theme preference
     * @param context Application context
     * @return theme value: "dark", "light", or "system" (default: "dark")
     */
    fun getAppTheme(context: Context): String {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(PREF_APP_THEME, "dark") ?: "dark"
    }

    /**
     * Sets app theme preference
     * @param context Application context
     * @param theme theme value: "dark", "light", or "system"
     */
    fun setAppTheme(context: Context, theme: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(PREF_APP_THEME, theme)
            .apply()
    }
}
