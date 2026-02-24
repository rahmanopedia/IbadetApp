package com.ibadetapp.data.cache

import com.ibadetapp.data.model.Surah
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class QuranCacheTest {

    private val testSurah = Surah(
        number = 1,
        name = "الفاتحة",
        turkishName = "Al-Fatihah",
        englishName = "The Opening",
        revelationType = "Meccan",
        numberOfAyahs = 7
    )

    @Before
    fun setUp() {
        QuranCache.clearCache()
    }

    @Test
    fun `getSurah should return null for uncached surah`() {
        val result = QuranCache.getSurah(1)
        assertNull(result)
    }

    @Test
    fun `cacheSurah should store surah in cache`() {
        QuranCache.cacheSurah(1, testSurah)
        val cached = QuranCache.getSurah(1)

        assertNotNull(cached)
        assertEquals(testSurah.number, cached?.number)
        assertEquals(testSurah.turkishName, cached?.turkishName)
    }

    @Test
    fun `getSurah should return cached surah`() {
        QuranCache.cacheSurah(1, testSurah)
        val result = QuranCache.getSurah(1)

        assertEquals(testSurah.number, result?.number)
        assertEquals("Al-Fatihah", result?.turkishName)
    }

    @Test
    fun `clearCache should remove all cached items`() {
        QuranCache.cacheSurah(1, testSurah)
        QuranCache.cacheSurah(2, testSurah.copy(number = 2, turkishName = "Al-Baqarah"))

        QuranCache.clearCache()

        assertNull(QuranCache.getSurah(1))
        assertNull(QuranCache.getSurah(2))
    }

    @Test
    fun `cacheSurah should overwrite existing surah`() {
        val surah1 = testSurah
        val surah2 = testSurah.copy(turkishName = "Updated Name")

        QuranCache.cacheSurah(1, surah1)
        QuranCache.cacheSurah(1, surah2)

        val cached = QuranCache.getSurah(1)
        assertEquals("Updated Name", cached?.turkishName)
    }

    @Test
    fun `getCacheStats should return cache information`() {
        QuranCache.cacheSurah(1, testSurah)
        val stats = QuranCache.getCacheStats()

        assertTrue(stats.contains("Cache size"))
        assertTrue(stats.contains("1"))
    }
}
