package com.ibadetapp.data.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ibadetapp.data.model.Zikir
import com.ibadetapp.data.model.ZikirSession

@Dao
interface ZikirDao {

    @Query("SELECT * FROM zikir_table ORDER BY id ASC")
    fun getAllZikirler(): LiveData<List<Zikir>>

    @Query("SELECT * FROM zikir_table WHERE id = :id")
    suspend fun getZikirById(id: Int): Zikir?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(zikir: Zikir): Long

    @Update
    suspend fun update(zikir: Zikir)

    @Delete
    suspend fun delete(zikir: Zikir)

    @Query("UPDATE zikir_table SET currentCount = :count WHERE id = :id")
    suspend fun updateCount(id: Int, count: Int)

    @Query("UPDATE zikir_table SET currentCount = 0 WHERE id = :id")
    suspend fun resetCount(id: Int)

    // Zikir Sessions
    @Insert
    suspend fun insertSession(session: ZikirSession): Long

    @Query("SELECT * FROM zikir_session_table ORDER BY completedAt DESC LIMIT 50")
    fun getRecentSessions(): LiveData<List<ZikirSession>>

    @Query("SELECT COUNT(*) FROM zikir_session_table WHERE isCompleted = 1")
    fun getCompletedSessionCount(): LiveData<Int>
}
