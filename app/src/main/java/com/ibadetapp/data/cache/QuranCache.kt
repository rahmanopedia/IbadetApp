package com.ibadetapp.data.cache

import com.ibadetapp.data.model.Surah
import java.util.concurrent.TimeUnit

/**
 * In-memory cache for Quran data
 * Improves performance by caching frequently accessed surahs
 */
object QuranCache {

    private const val CACHE_DURATION_MINUTES = 60L
    private const val MAX_CACHE_SIZE = 30

    private val cache = object : LinkedHashMap<Int, CachedSurah>(MAX_CACHE_SIZE, 0.75f, true) {
        override fun removeEldestEntry(eldest: Map.Entry<Int, CachedSurah>?): Boolean {
            return size > MAX_CACHE_SIZE
        }
    }

    /**
     * Gets a cached surah if available and not expired
     * @param surahNumber The surah number to retrieve
     * @return Cached surah or null if not in cache or expired
     */
    fun getSurah(surahNumber: Int): Surah? {
        val cached = cache[surahNumber]
        return if (cached != null && !cached.isExpired()) {
            cached.surah
        } else {
            cache.remove(surahNumber)
            null
        }
    }

    /**
     * Caches a surah with timestamp
     * @param surahNumber The surah number
     * @param surah The surah data to cache
     */
    fun cacheSurah(surahNumber: Int, surah: Surah) {
        cache[surahNumber] = CachedSurah(surah, System.currentTimeMillis())
    }

    /**
     * Clears all cached data
     */
    fun clearCache() {
        cache.clear()
    }

    /**
     * Gets cache statistics for debugging
     * @return String with cache size and info
     */
    fun getCacheStats(): String {
        return "Cache size: ${cache.size}/$MAX_CACHE_SIZE items"
    }

    /**
     * Data class for storing surah with cache timestamp
     */
    private data class CachedSurah(
        val surah: Surah,
        val cachedAt: Long
    ) {
        fun isExpired(): Boolean {
            val ageMinutes = (System.currentTimeMillis() - cachedAt) / 1000 / 60
            return ageMinutes > CACHE_DURATION_MINUTES
        }
    }
}
