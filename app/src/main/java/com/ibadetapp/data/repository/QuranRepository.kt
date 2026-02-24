package com.ibadetapp.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ibadetapp.data.cache.QuranCache
import com.ibadetapp.data.model.Ayah
import com.ibadetapp.data.model.BookmarkedAyah
import com.ibadetapp.data.model.Surah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuranRepository(
    private val context: Context,
    private val bookmarkDao: BookmarkDao
) {

    private val gson = Gson()

    suspend fun getAllSurahs(): List<Surah> = withContext(Dispatchers.IO) {
        try {
            val json = context.assets.open("quran/surah_list.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Surah>>() {}.type
            gson.fromJson<List<Surah>>(json, type) ?: emptyList()
        } catch (e: Exception) {
            Log.e(TAG, "Error loading surah list", e)
            emptyList()
        }
    }

    suspend fun getSurahDetail(surahNumber: Int): Surah? = withContext(Dispatchers.IO) {
        // Check cache first
        QuranCache.getSurah(surahNumber)?.let {
            Log.d(TAG, "Surah $surahNumber loaded from cache")
            return@withContext it
        }

        try {
            val json = context.assets.open("quran/surah_$surahNumber.json").bufferedReader().use { it.readText() }
            val surah = gson.fromJson(json, Surah::class.java)
            // Cache the result
            surah?.let { QuranCache.cacheSurah(surahNumber, it) }
            surah
        } catch (e: Exception) {
            Log.e(TAG, "Error loading surah detail for surah $surahNumber", e)
            null
        }
    }

    // Bookmarks
    fun getAllBookmarks() = bookmarkDao.getAllBookmarks()

    suspend fun addBookmark(bookmark: BookmarkedAyah) = bookmarkDao.insert(bookmark)

    suspend fun removeBookmark(surahNumber: Int, ayahNumber: Int) =
        bookmarkDao.deleteByAyah(surahNumber, ayahNumber)

    suspend fun isBookmarked(surahNumber: Int, ayahNumber: Int): Boolean =
        bookmarkDao.isBookmarked(surahNumber, ayahNumber)

    companion object {
        private const val TAG = "QuranRepository"
    }
}
