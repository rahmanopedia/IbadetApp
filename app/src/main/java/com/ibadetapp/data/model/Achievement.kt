package com.ibadetapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents an achievement in the app
 * Users earn these by completing certain tasks
 */
@Entity(tableName = "achievements")
data class Achievement(
    @PrimaryKey val achievementId: String,
    val title: String,
    val description: String,
    val iconResourceId: Int = 0,
    val condition: String = "", // e.g., "7_day_streak", "100_zikir_completed"
    val category: String = "", // e.g., "Streak", "Zikir", "Quran"
    val points: Int = 0 // Reward points
)

@Entity(tableName = "user_achievements")
data class UserAchievement(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val achievementId: String,
    val unlockedAt: Long = System.currentTimeMillis(),
    val notificationShown: Boolean = false
)

@Entity(tableName = "streak_entries")
data class StreakEntry(
    @PrimaryKey val date: String, // Format: yyyy-MM-dd
    val zikirCompleted: Boolean = false,
    val quranRead: Boolean = false,
    val duaaRecited: Boolean = false,
    val streakDay: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "milestones")
data class Milestone(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String, // e.g., "streak_days", "zikir_count"
    val value: Int,
    val reachedAt: Long = System.currentTimeMillis()
)

// Pre-defined achievements
object AchievementsList {
    fun getAllAchievements() = listOf(
        Achievement("first_zikir", "İlk Adım", "İlk zikiri tamamladı", category = "Zikir"),
        Achievement("zikir_7days", "Haftalık", "7 gün kesintisiz zikir yaptı", category = "Streak"),
        Achievement("zikir_30days", "Aylık", "30 gün kesintisiz zikir yaptı", category = "Streak"),
        Achievement("zikir_100days", "Yüz Gün", "100 gün kesintisiz zikir yaptı", category = "Streak"),
        Achievement("bookmark_10", "Koleksiyoncu", "10 ayet yer imlerine ekledi", category = "Quran"),
        Achievement("bookmark_50", "Kuran Muhibbi", "50 ayet yer imlerine ekledi", category = "Quran"),
        Achievement("zikir_1000", "Binler", "1000 zikir tamamladı", category = "Zikir"),
        Achievement("quran_reader", "Kuran Okuyucusu", "İlk kez Kuran'da ayet okudu", category = "Quran"),
        Achievement("night_owl", "Gece Kuşu", "Gece yarısından sonra ibadət yaptı", category = "General"),
        Achievement("comeback", "Geri Dönüş", "Aradan sonra ibadête geri döndü", category = "General")
    )
}
