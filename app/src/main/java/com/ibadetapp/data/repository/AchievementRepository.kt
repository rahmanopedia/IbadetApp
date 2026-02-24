package com.ibadetapp.data.repository

import com.ibadetapp.data.model.*
import com.ibadetapp.util.StreakCalculator
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing achievements and streaks
 */
class AchievementRepository(
    private val achievementDao: AchievementDao,
    private val streakDao: StreakDao
) {

    init {
        // Initialize achievements on first load
        // This would be called in an init block or separate initialization
    }

    // ===== Streak Operations =====

    suspend fun recordDailyCompletion(
        zikirCompleted: Boolean = false,
        quranRead: Boolean = false,
        duaaRecited: Boolean = false
    ) {
        val today = StreakCalculator.getTodayDateString()
        val entry = StreakEntry(
            date = today,
            zikirCompleted = zikirCompleted,
            quranRead = quranRead,
            duaaRecited = duaaRecited,
            streakDay = calculateStreakDay()
        )
        streakDao.insertStreakEntry(entry)
    }

    fun getRecentStreaks(): Flow<List<StreakEntry>> = streakDao.getRecentStreaks()

    fun getWeeklyCompletions(): Flow<Int> = streakDao.getWeeklyCompletions()

    suspend fun getCurrentStreak(): Int {
        val entries = streakDao.getAllStreaks()
        return StreakCalculator.calculateCurrentStreak(
            entries.map { it.date to it.zikirCompleted }
        )
    }

    suspend fun getLongestStreak(): Int = streakDao.getLongestStreak() ?: 0

    private suspend fun calculateStreakDay(): Int {
        val entries = streakDao.getAllStreaks()
        return StreakCalculator.calculateCurrentStreak(
            entries.map { it.date to it.zikirCompleted }
        )
    }

    // ===== Achievement Operations =====

    suspend fun initializeAchievements() {
        val achievements = AchievementsList.getAllAchievements()
        achievementDao.insertMultipleAchievements(achievements)
    }

    suspend fun unlockAchievement(achievementId: String) {
        val achievement = achievementDao.getAchievement(achievementId) ?: return
        val userAchievement = UserAchievement(
            achievementId = achievementId,
            unlockedAt = System.currentTimeMillis(),
            notificationShown = false
        )
        achievementDao.insertUserAchievement(userAchievement)
    }

    fun getUserAchievements(): Flow<List<UserAchievement>> =
        achievementDao.getUserAchievements()

    fun getTotalUnlockedCount(): Flow<Int> = achievementDao.getTotalUnlocked()

    suspend fun checkAndUnlockMilestones() {
        val currentStreak = getCurrentStreak()
        val milestone = StreakCalculator.getMilestoneAchieved(currentStreak)

        milestone?.let {
            val isUnlocked = achievementDao.isAchievementUnlocked(it)
            if (!isUnlocked) {
                unlockAchievement(it)
            }
        }
    }

    suspend fun isAchievementUnlocked(achievementId: String): Boolean =
        achievementDao.isAchievementUnlocked(achievementId)
}
