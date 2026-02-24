package com.ibadetapp.data.repository

import androidx.room.*
import com.ibadetapp.data.model.StreakEntry
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for streak entries
 */
@Dao
interface StreakDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStreakEntry(streakEntry: StreakEntry)

    @Query("SELECT * FROM streak_entries WHERE date = :date")
    suspend fun getStreakEntry(date: String): StreakEntry?

    @Query("SELECT * FROM streak_entries ORDER BY date DESC LIMIT 30")
    fun getRecentStreaks(): Flow<List<StreakEntry>>

    @Query("SELECT * FROM streak_entries ORDER BY date DESC")
    suspend fun getAllStreaks(): List<StreakEntry>

    @Query("SELECT COUNT(*) FROM streak_entries WHERE streakDay > 0 AND date >= date('now', '-7 days')")
    fun getWeeklyCompletions(): Flow<Int>

    @Query("SELECT MAX(streakDay) FROM streak_entries")
    suspend fun getLongestStreak(): Int?

    @Query("SELECT COUNT(*) FROM streak_entries WHERE zikirCompleted = 1")
    fun getTotalZikirDays(): Flow<Int>

    @Delete
    suspend fun deleteStreakEntry(streakEntry: StreakEntry)

    @Query("DELETE FROM streak_entries WHERE date < date('now', '-1 year')")
    suspend fun deleteOldEntries()
}
