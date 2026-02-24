package com.ibadetapp.data.export

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.ibadetapp.data.model.BookmarkedAyah
import com.ibadetapp.data.model.Zikir
import java.io.File

/**
 * Handles import of user data from JSON format
 * Supports restoring bookmarks and custom zikirler
 */
class DataImporter(private val context: Context) {

    private val gson = Gson()

    /**
     * Import result
     */
    sealed class ImportResult {
        data class Success(
            val bookmarksCount: Int,
            val zikirlerCount: Int
        ) : ImportResult()

        data class Error(val message: String, val exception: Exception? = null) : ImportResult()
    }

    /**
     * Imports data from JSON file
     * @param file JSON file to import
     * @return ImportResult with counts or error
     */
    fun importFromJson(file: File): ImportResult {
        return try {
            val json = file.readText()
            val jsonElement = gson.fromJson(json, JsonElement::class.java)
            val jsonObject = jsonElement.asJsonObject

            val bookmarks = mutableListOf<BookmarkedAyah>()
            val zikirler = mutableListOf<Zikir>()

            // Parse bookmarks
            if (jsonObject.has("bookmarks")) {
                val bookmarksArray = jsonObject.getAsJsonArray("bookmarks")
                for (item in bookmarksArray) {
                    try {
                        val bookmark = gson.fromJson(item, BookmarkedAyah::class.java)
                        bookmarks.add(bookmark)
                    } catch (e: Exception) {
                        return ImportResult.Error("Bookmark import hatası", e)
                    }
                }
            }

            // Parse zikirler
            if (jsonObject.has("zikirler")) {
                val zikirlerArray = jsonObject.getAsJsonArray("zikirler")
                for (item in zikirlerArray) {
                    try {
                        val zikir = gson.fromJson(item, Zikir::class.java)
                        zikirler.add(zikir)
                    } catch (e: Exception) {
                        return ImportResult.Error("Zikir import hatası", e)
                    }
                }
            }

            ImportResult.Success(bookmarks.size, zikirler.size)
        } catch (e: Exception) {
            ImportResult.Error("Dosya okuma hatası: ${e.message}", e)
        }
    }

    /**
     * Imports bookmarks from JSON file
     * @param file JSON file containing bookmarks
     * @return List of imported bookmarks or error
     */
    fun importBookmarks(file: File): Result<List<BookmarkedAyah>> {
        return try {
            val json = file.readText()
            val jsonElement = gson.fromJson(json, JsonElement::class.java)
            val jsonObject = jsonElement.asJsonObject

            val bookmarks = mutableListOf<BookmarkedAyah>()

            if (jsonObject.has("bookmarks")) {
                val bookmarksArray = jsonObject.getAsJsonArray("bookmarks")
                for (item in bookmarksArray) {
                    val bookmark = gson.fromJson(item, BookmarkedAyah::class.java)
                    bookmarks.add(bookmark)
                }
            }

            Result.success(bookmarks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Imports zikirler from JSON file
     * @param file JSON file containing zikirler
     * @return List of imported zikirler or error
     */
    fun importZikirler(file: File): Result<List<Zikir>> {
        return try {
            val json = file.readText()
            val jsonElement = gson.fromJson(json, JsonElement::class.java)
            val jsonObject = jsonElement.asJsonObject

            val zikirler = mutableListOf<Zikir>()

            if (jsonObject.has("zikirler")) {
                val zikirlerArray = jsonObject.getAsJsonArray("zikirler")
                for (item in zikirlerArray) {
                    val zikir = gson.fromJson(item, Zikir::class.java)
                    zikirler.add(zikir)
                }
            }

            Result.success(zikirler)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Validates JSON file format
     * @param file File to validate
     * @return true if valid, false otherwise
     */
    fun validateJsonFormat(file: File): Boolean {
        return try {
            val json = file.readText()
            val jsonElement = gson.fromJson(json, JsonElement::class.java)
            jsonElement.isJsonObject
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Gets import file info
     * @param file File to analyze
     * @return Info string
     */
    fun getFileInfo(file: File): String {
        return buildString {
            append("Dosya: ${file.name}\n")
            append("Boyut: ${formatFileSize(file.length())}\n")
            append("Son Değiştirilme: ${file.lastModified()}")
        }
    }

    /**
     * Formats file size for display
     * @param bytes Size in bytes
     * @return Formatted size string
     */
    private fun formatFileSize(bytes: Long): String {
        return when {
            bytes <= 0 -> "0 B"
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            else -> "${bytes / (1024 * 1024)} MB"
        }
    }
}
