package com.ibadetapp.data.repository

import androidx.room.*
import com.ibadetapp.data.model.Achievement
import com.ibadetapp.data.model.UserAchievement
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for achievements
 */
@Dao
interface AchievementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAchievement(achievement: Achievement)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMultipleAchievements(achievements: List<Achievement>)

    @Query("SELECT * FROM achievements")
    suspend fun getAllAchievements(): List<Achievement>

    @Query("SELECT * FROM achievements WHERE achievementId = :id")
    suspend fun getAchievement(id: String): Achievement?

    // User achievements
    @Insert
    suspend fun insertUserAchievement(userAchievement: UserAchievement)

    @Query("SELECT * FROM user_achievements ORDER BY unlockedAt DESC")
    fun getUserAchievements(): Flow<List<UserAchievement>>

    @Query("SELECT COUNT(*) FROM user_achievements")
    fun getTotalUnlocked(): Flow<Int>

    @Query("SELECT * FROM user_achievements WHERE achievementId = :achievementId LIMIT 1")
    suspend fun getUserAchievement(achievementId: String): UserAchievement?

    @Query("SELECT CASE WHEN (SELECT COUNT(*) FROM user_achievements WHERE achievementId = :achievementId) > 0 THEN 1 ELSE 0 END")
    suspend fun isAchievementUnlocked(achievementId: String): Boolean

    @Update
    suspend fun updateUserAchievement(userAchievement: UserAchievement)

    @Delete
    suspend fun deleteUserAchievement(userAchievement: UserAchievement)
}
