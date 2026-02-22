package com.ibadetapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_ayah_table")
data class BookmarkedAyah(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val surahNumber: Int,
    val surahName: String,
    val ayahNumber: Int,
    val arabicText: String,
    val turkishText: String,
    val savedAt: Long = System.currentTimeMillis()
)
