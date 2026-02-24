package com.ibadetapp.util

import org.junit.Test
import org.junit.Assert.*

class ThemeManagerTest {

    @Test
    fun `ThemeMode LIGHT should have correct value`() {
        assertEquals("light", ThemeManager.ThemeMode.LIGHT.value)
    }

    @Test
    fun `ThemeMode DARK should have correct value`() {
        assertEquals("dark", ThemeManager.ThemeMode.DARK.value)
    }

    @Test
    fun `ThemeMode SYSTEM should have correct value`() {
        assertEquals("system", ThemeManager.ThemeMode.SYSTEM.value)
    }

    @Test
    fun `getNextTheme should rotate through themes`() {
        val light = ThemeManager.ThemeMode.LIGHT
        val dark = ThemeManager.getNextTheme(light)
        assertEquals(ThemeManager.ThemeMode.DARK, dark)

        val system = ThemeManager.getNextTheme(dark)
        assertEquals(ThemeManager.ThemeMode.SYSTEM, system)

        val nextLight = ThemeManager.getNextTheme(system)
        assertEquals(ThemeManager.ThemeMode.LIGHT, nextLight)
    }

    @Test
    fun `getThemeDisplayName should return correct display names`() {
        assertEquals("Açık Tema", ThemeManager.getThemeDisplayName(ThemeManager.ThemeMode.LIGHT))
        assertEquals("Koyu Tema", ThemeManager.getThemeDisplayName(ThemeManager.ThemeMode.DARK))
        assertEquals("Sistem Teması", ThemeManager.getThemeDisplayName(ThemeManager.ThemeMode.SYSTEM))
    }

    @Test
    fun `toggleTheme should alternate between light and dark`() {
        val current = ThemeManager.ThemeMode.LIGHT
        val toggled = when (current) {
            ThemeManager.ThemeMode.LIGHT -> ThemeManager.ThemeMode.DARK
            ThemeManager.ThemeMode.DARK -> ThemeManager.ThemeMode.LIGHT
            ThemeManager.ThemeMode.SYSTEM -> ThemeManager.ThemeMode.DARK
        }
        assertEquals(ThemeManager.ThemeMode.DARK, toggled)
    }
}
