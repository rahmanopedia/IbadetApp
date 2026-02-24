package com.ibadetapp.data.export

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ibadetapp.data.model.BookmarkedAyah
import com.ibadetapp.data.model.Zikir
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Handles export of user data to JSON format
 * Supports backing up bookmarks and custom zikirler
 */
class DataExporter(private val context: Context) {

    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    /**
     * Export data container
     */
    data class ExportData(
        val version: String = "1.0",
        val exportedAt: String,
        val bookmarks: List<BookmarkedAyah>,
        val zikirler: List<Zikir>
    )

    /**
     * Exports bookmarks and zikirler to JSON file
     * @param bookmarks List of bookmarks to export
     * @param zikirler List of zikirler to export
     * @param fileName Optional custom file name
     * @return File with exported data
     */
    fun exportToJson(
        bookmarks: List<BookmarkedAyah>,
        zikirler: List<Zikir>,
        fileName: String = "ibadet_export_${System.currentTimeMillis()}.json"
    ): File {
        val exportData = ExportData(
            exportedAt = dateFormat.format(Date()),
            bookmarks = bookmarks,
            zikirler = zikirler
        )

        val json = gson.toJson(exportData)
        val file = File(context.filesDir, fileName)
        file.writeText(json)

        return file
    }

    /**
     * Exports bookmarks only
     * @param bookmarks List of bookmarks to export
     * @param fileName Optional custom file name
     * @return File with exported bookmarks
     */
    fun exportBookmarks(
        bookmarks: List<BookmarkedAyah>,
        fileName: String = "bookmarks_export_${System.currentTimeMillis()}.json"
    ): File {
        val json = gson.toJson(mapOf(
            "exportedAt" to dateFormat.format(Date()),
            "bookmarks" to bookmarks
        ))

        val file = File(context.filesDir, fileName)
        file.writeText(json)

        return file
    }

    /**
     * Exports custom zikirler only
     * @param zikirler List of custom zikirler to export
     * @param fileName Optional custom file name
     * @return File with exported zikirler
     */
    fun exportZikirler(
        zikirler: List<Zikir>,
        fileName: String = "zikirler_export_${System.currentTimeMillis()}.json"
    ): File {
        val customZikirler = zikirler.filter { it.isCustom }
        val json = gson.toJson(mapOf(
            "exportedAt" to dateFormat.format(Date()),
            "zikirler" to customZikirler
        ))

        val file = File(context.filesDir, fileName)
        file.writeText(json)

        return file
    }

    /**
     * Gets export file size in bytes
     * @param file Export file
     * @return File size in bytes
     */
    fun getExportSize(file: File): Long {
        return file.length()
    }

    /**
     * Formats file size for display
     * @param bytes Size in bytes
     * @return Formatted size string
     */
    fun formatFileSize(bytes: Long): String {
        return when {
            bytes <= 0 -> "0 B"
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            else -> "${bytes / (1024 * 1024)} MB"
        }
    }
}
