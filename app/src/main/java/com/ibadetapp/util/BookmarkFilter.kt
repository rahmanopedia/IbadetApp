package com.ibadetapp.util

import com.ibadetapp.data.model.BookmarkedAyah

/**
 * Utility for filtering and searching bookmarks
 */
object BookmarkFilter {

    /**
     * Filters bookmarks by surah name
     * @param bookmarks List of bookmarks to filter
     * @param surahName Surah name to filter by
     * @return Filtered list of bookmarks
     */
    fun filterBySurah(bookmarks: List<BookmarkedAyah>, surahName: String): List<BookmarkedAyah> {
        if (surahName.isEmpty()) return bookmarks
        return bookmarks.filter { it.surahName.contains(surahName, ignoreCase = true) }
    }

    /**
     * Searches bookmarks by text in arabic or turkish content
     * @param bookmarks List of bookmarks to search
     * @param query Search query
     * @return Filtered list matching the query
     */
    fun search(bookmarks: List<BookmarkedAyah>, query: String): List<BookmarkedAyah> {
        if (query.isEmpty()) return bookmarks
        return bookmarks.filter { bookmark ->
            bookmark.surahName.contains(query, ignoreCase = true) ||
                    bookmark.turkishText.contains(query, ignoreCase = true) ||
                    bookmark.arabicText.contains(query) ||
                    bookmark.ayahNumber.toString() == query
        }
    }

    /**
     * Filters bookmarks within a date range
     * @param bookmarks List of bookmarks to filter
     * @param fromMillis Start date in milliseconds
     * @param toMillis End date in milliseconds
     * @return Filtered list within date range
     */
    fun filterByDateRange(
        bookmarks: List<BookmarkedAyah>,
        fromMillis: Long,
        toMillis: Long
    ): List<BookmarkedAyah> {
        return bookmarks.filter { it.savedAt in fromMillis..toMillis }
    }

    /**
     * Sorts bookmarks by most recent first
     * @param bookmarks List of bookmarks to sort
     * @return Sorted list (most recent first)
     */
    fun sortByDateDescending(bookmarks: List<BookmarkedAyah>): List<BookmarkedAyah> {
        return bookmarks.sortedByDescending { it.savedAt }
    }

    /**
     * Sorts bookmarks by surah number ascending
     * @param bookmarks List of bookmarks to sort
     * @return Sorted list by surah number
     */
    fun sortBySurahNumber(bookmarks: List<BookmarkedAyah>): List<BookmarkedAyah> {
        return bookmarks.sortedWith(compareBy({ it.surahNumber }, { it.ayahNumber }))
    }

    /**
     * Gets unique surahs from bookmarks
     * @param bookmarks List of bookmarks
     * @return List of unique surah names
     */
    fun getUniqueSurahs(bookmarks: List<BookmarkedAyah>): List<String> {
        return bookmarks.map { it.surahName }.distinct()
    }
}
