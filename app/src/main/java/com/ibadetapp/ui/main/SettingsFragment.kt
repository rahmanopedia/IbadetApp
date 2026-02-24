package com.ibadetapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.ibadetapp.R

/**
 * Settings fragment for user preferences
 * Allows users to configure app behavior (vibration, font size, theme, etc.)
 */
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        // Vibration preference
        val vibrationPref: SwitchPreferenceCompat? = findPreference("vibration_enabled")
        vibrationPref?.apply {
            title = "Titreşim Aktif"
            summary = "Zikir sayacında titreşim geri bildirimini etkinleştir"
            setDefaultValue(true)
        }

        // Font size preference
        val fontSizePref: Preference? = findPreference("quran_font_size")
        fontSizePref?.apply {
            title = "Quran Yazı Boyutu"
            summary = "Arapça metinin yazı boyutunu ayarla"
        }

        // Theme preference
        val themePref: Preference? = findPreference("app_theme")
        themePref?.apply {
            title = "Uygulama Teması"
            summary = "Açık veya Koyu tema seç"
        }

        // About preference
        val aboutPref: Preference? = findPreference("about")
        aboutPref?.apply {
            title = "Hakkında"
            summary = "Versiyon 1.0 - İbadet Uygulaması"
        }
    }

    companion object {
        const val PREF_VIBRATION_ENABLED = "vibration_enabled"
        const val PREF_QURAN_FONT_SIZE = "quran_font_size"
        const val PREF_APP_THEME = "app_theme"
    }
}
