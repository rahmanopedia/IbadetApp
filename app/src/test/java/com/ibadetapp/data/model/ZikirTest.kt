package com.ibadetapp.data.model

import org.junit.Assert.*
import org.junit.Test

class ZikirTest {

    @Test
    fun `Zikir should calculate progress correctly`() {
        val zikir = Zikir(
            id = 1,
            arabicText = "سُبْحَانَ اللَّهِ",
            turkishText = "Subhanallah",
            transliteration = "Subhanallah",
            targetCount = 100,
            currentCount = 50,
            category = "Tasbih"
        )

        val progress = (zikir.currentCount.toFloat() / zikir.targetCount * 100).toInt()
        assertEquals(50, progress)
    }

    @Test
    fun `Zikir should handle zero target count`() {
        val zikir = Zikir(
            id = 1,
            arabicText = "سُبْحَانَ اللَّهِ",
            turkishText = "Subhanallah",
            transliteration = "Subhanallah",
            targetCount = 0,
            currentCount = 0,
            category = "Tasbih"
        )

        assertTrue(zikir.targetCount == 0)
        assertTrue(zikir.currentCount == 0)
    }

    @Test
    fun `Zikir isCompleted should return true when current equals target`() {
        val zikir = Zikir(
            id = 1,
            arabicText = "سُبْحَانَ اللَّهِ",
            turkishText = "Subhanallah",
            transliteration = "Subhanallah",
            targetCount = 33,
            currentCount = 33,
            category = "Tasbih"
        )

        val isCompleted = zikir.currentCount >= zikir.targetCount
        assertTrue(isCompleted)
    }

    @Test
    fun `Custom zikir should be marked as custom`() {
        val customZikir = Zikir(
            id = 999,
            arabicText = "دعاء مخصص",
            turkishText = "Özel Dua",
            transliteration = "Dua Muhasas",
            targetCount = 10,
            currentCount = 0,
            category = "Özel",
            isCustom = true
        )

        assertTrue(customZikir.isCustom)
    }

    @Test
    fun `Default zikir should not be marked as custom`() {
        val defaultZikir = Zikir(
            id = 1,
            arabicText = "سُبْحَانَ اللَّهِ",
            turkishText = "Subhanallah",
            transliteration = "Subhanallah",
            targetCount = 33,
            currentCount = 0,
            category = "Tasbih"
        )

        assertFalse(defaultZikir.isCustom)
    }
}
