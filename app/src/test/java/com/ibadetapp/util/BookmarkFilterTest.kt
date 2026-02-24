package com.ibadetapp.util

import com.ibadetapp.data.model.BookmarkedAyah
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class BookmarkFilterTest {

    private val testBookmarks = listOf(
        BookmarkedAyah(
            id = 1,
            surahNumber = 1,
            surahName = "Al-Fatihah",
            ayahNumber = 1,
            arabicText = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
            turkishText = "Bismillah",
            savedAt = System.currentTimeMillis()
        ),
        BookmarkedAyah(
            id = 2,
            surahNumber = 2,
            surahName = "Al-Baqarah",
            ayahNumber = 255,
            arabicText = "اللَّهُ لَا إِلَٰهَ إِلَّا هُوَ",
            turkishText = "Ayet-el Kürsi",
            savedAt = System.currentTimeMillis() - 86400000
        ),
        BookmarkedAyah(
            id = 3,
            surahNumber = 36,
            surahName = "Yasin",
            ayahNumber = 1,
            arabicText = "يس",
            turkishText = "Yasin",
            savedAt = System.currentTimeMillis() - 172800000
        )
    )

    @Test
    fun `filterBySurah should return bookmarks matching surah name`() {
        val result = BookmarkFilter.filterBySurah(testBookmarks, "Baqarah")
        assertEquals(1, result.size)
        assertEquals("Al-Baqarah", result[0].surahName)
    }

    @Test
    fun `filterBySurah with empty query should return all bookmarks`() {
        val result = BookmarkFilter.filterBySurah(testBookmarks, "")
        assertEquals(testBookmarks.size, result.size)
    }

    @Test
    fun `search should find bookmarks by Turkish text`() {
        val result = BookmarkFilter.search(testBookmarks, "Kürsi")
        assertEquals(1, result.size)
        assertEquals("Ayet-el Kürsi", result[0].turkishText)
    }

    @Test
    fun `search should be case insensitive`() {
        val result = BookmarkFilter.search(testBookmarks, "FATIHAH")
        assertEquals(1, result.size)
    }

    @Test
    fun `sortByDateDescending should return most recent first`() {
        val result = BookmarkFilter.sortByDateDescending(testBookmarks)
        assertTrue(result[0].savedAt >= result[1].savedAt)
        assertTrue(result[1].savedAt >= result[2].savedAt)
    }

    @Test
    fun `sortBySurahNumber should order by surah number`() {
        val result = BookmarkFilter.sortBySurahNumber(testBookmarks)
        assertEquals(1, result[0].surahNumber)
        assertEquals(2, result[1].surahNumber)
        assertEquals(36, result[2].surahNumber)
    }

    @Test
    fun `getUniqueSurahs should return distinct surah names`() {
        val result = BookmarkFilter.getUniqueSurahs(testBookmarks)
        assertEquals(3, result.size)
        assertTrue(result.contains("Al-Fatihah"))
        assertTrue(result.contains("Al-Baqarah"))
        assertTrue(result.contains("Yasin"))
    }
}
