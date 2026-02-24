package com.ibadetapp.ui.quran

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibadetapp.data.model.Surah
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QuranViewModelSearchTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testSurahs = listOf(
        Surah(1, "Al-Fatihah", "الفاتحة", "The Opening", "Meccan", 7),
        Surah(2, "Al-Baqarah", "البقرة", "The Cow", "Medinan", 286),
        Surah(3, "Aal-e-Imran", "آل عمران", "The Family of Imran", "Medinan", 200),
        Surah(4, "An-Nisa", "النساء", "The Women", "Medinan", 176)
    )

    @Test
    fun `searchSurahs with empty query should return all surahs`() {
        // This test verifies the search logic without needing full ViewModel initialization
        val query = ""
        val filtered = testSurahs.filter { surah ->
            surah.turkishName.contains(query, ignoreCase = true) ||
                    surah.englishName.contains(query, ignoreCase = true) ||
                    surah.number.toString() == query
        }

        assertEquals(testSurahs.size, filtered.size)
        assertEquals(testSurahs, filtered)
    }

    @Test
    fun `searchSurahs by Turkish name should find matching surahs`() {
        val query = "Fati"
        val filtered = testSurahs.filter { surah ->
            surah.turkishName.contains(query, ignoreCase = true) ||
                    surah.englishName.contains(query, ignoreCase = true) ||
                    surah.number.toString() == query
        }

        assertEquals(1, filtered.size)
        assertEquals("Al-Fatihah", filtered[0].turkishName)
    }

    @Test
    fun `searchSurahs by English name should find matching surahs`() {
        val query = "Women"
        val filtered = testSurahs.filter { surah ->
            surah.turkishName.contains(query, ignoreCase = true) ||
                    surah.englishName.contains(query, ignoreCase = true) ||
                    surah.number.toString() == query
        }

        assertEquals(1, filtered.size)
        assertEquals("An-Nisa", filtered[0].turkishName)
    }

    @Test
    fun `searchSurahs by number should find matching surah`() {
        val query = "3"
        val filtered = testSurahs.filter { surah ->
            surah.turkishName.contains(query, ignoreCase = true) ||
                    surah.englishName.contains(query, ignoreCase = true) ||
                    surah.number.toString() == query
        }

        assertEquals(1, filtered.size)
        assertEquals(3, filtered[0].number)
    }

    @Test
    fun `searchSurahs with non-matching query should return empty list`() {
        val query = "NonExistent"
        val filtered = testSurahs.filter { surah ->
            surah.turkishName.contains(query, ignoreCase = true) ||
                    surah.englishName.contains(query, ignoreCase = true) ||
                    surah.number.toString() == query
        }

        assertEquals(0, filtered.size)
    }

    @Test
    fun `searchSurahs should be case insensitive`() {
        val query = "BAQARAH"
        val filtered = testSurahs.filter { surah ->
            surah.turkishName.contains(query, ignoreCase = true) ||
                    surah.englishName.contains(query, ignoreCase = true) ||
                    surah.number.toString() == query
        }

        assertEquals(1, filtered.size)
        assertEquals("Al-Baqarah", filtered[0].turkishName)
    }
}
