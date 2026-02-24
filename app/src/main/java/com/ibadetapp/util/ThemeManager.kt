package com.ibadetapp.util

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES

/**
 * Centralized theme management utility
 * Handles dark/light/system theme switching with persistence
 */
object ThemeManager {

    private const val PREF_KEY_THEME = "app_theme_mode"

    /**
     * Theme modes
     */
    enum class ThemeMode(val value: String, val nightMode: Int) {
        LIGHT("light", MODE_NIGHT_NO),
        DARK("dark", MODE_NIGHT_YES),
        SYSTEM("system", MODE_NIGHT_FOLLOW_SYSTEM)
    }

    /**
     * Applies theme to the app
     * @param context Application context
     * @param mode Theme mode to apply
     */
    fun applyTheme(context: Context, mode: ThemeMode) {
        AppCompatDelegate.setDefaultNightMode(mode.nightMode)
        PreferencesManager.setAppTheme(context, mode.value)
    }

    /**
     * Gets current theme mode from preferences
     * @param context Application context
     * @return Current theme mode
     */
    fun getCurrentTheme(context: Context): ThemeMode {
        val value = PreferencesManager.getAppTheme(context)
        return ThemeMode.values().find { it.value == value } ?: ThemeMode.DARK
    }

    /**
     * Gets next theme in rotation (Light -> Dark -> System -> Light)
     * @param current Current theme mode
     * @return Next theme mode
     */
    fun getNextTheme(current: ThemeMode): ThemeMode {
        return when (current) {
            ThemeMode.LIGHT -> ThemeMode.DARK
            ThemeMode.DARK -> ThemeMode.SYSTEM
            ThemeMode.SYSTEM -> ThemeMode.LIGHT
        }
    }

    /**
     * Toggles between dark and light theme
     * @param context Application context
     * @return New theme mode
     */
    fun toggleTheme(context: Context): ThemeMode {
        val current = getCurrentTheme(context)
        val next = when (current) {
            ThemeMode.LIGHT -> ThemeMode.DARK
            ThemeMode.DARK -> ThemeMode.LIGHT
            ThemeMode.SYSTEM -> ThemeMode.DARK
        }
        applyTheme(context, next)
        return next
    }

    /**
     * Gets human-readable theme name
     * @param mode Theme mode
     * @return Display name
     */
    fun getThemeDisplayName(mode: ThemeMode): String {
        return when (mode) {
            ThemeMode.LIGHT -> "Açık Tema"
            ThemeMode.DARK -> "Koyu Tema"
            ThemeMode.SYSTEM -> "Sistem Teması"
        }
    }

    /**
     * Initializes theme on app startup
     * @param context Application context
     */
    fun initializeTheme(context: Context) {
        val theme = getCurrentTheme(context)
        AppCompatDelegate.setDefaultNightMode(theme.nightMode)
    }
}
