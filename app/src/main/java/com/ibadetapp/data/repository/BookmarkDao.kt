package com.ibadetapp.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ibadetapp.data.model.BookmarkedAyah

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmarked_ayah_table ORDER BY savedAt DESC")
    fun getAllBookmarks(): LiveData<List<BookmarkedAyah>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookmark: BookmarkedAyah): Long

    @Delete
    suspend fun delete(bookmark: BookmarkedAyah)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_ayah_table WHERE surahNumber = :surahNumber AND ayahNumber = :ayahNumber)")
    suspend fun isBookmarked(surahNumber: Int, ayahNumber: Int): Boolean

    @Query("DELETE FROM bookmarked_ayah_table WHERE surahNumber = :surahNumber AND ayahNumber = :ayahNumber")
    suspend fun deleteByAyah(surahNumber: Int, ayahNumber: Int)
}
