package com.ibadetapp.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Calculates and manages user streaks
 */
object StreakCalculator {

    fun getTodayDateString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return sdf.format(Date())
    }

    fun getYesterdayDateString(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return sdf.format(calendar.time)
    }

    fun getDayOfYear(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    }

    fun calculateCurrentStreak(
        streakEntries: List<Pair<String, Boolean>> // (dateString, isCompleted)
    ): Int {
        if (streakEntries.isEmpty()) return 0

        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()

        // Sort by date descending
        val sortedEntries = streakEntries.sortedByDescending { it.first }

        var currentStreak = 0

        for ((date, isCompleted) in sortedEntries) {
            if (isCompleted) {
                currentStreak++
            } else {
                // Break if a day wasn't completed
                if (date == today || date == yesterday) {
                    continue // Skip checking non-current days
                }
                break
            }
        }

        return currentStreak
    }

    fun shouldShowStreakReminder(lastCompletionDate: String?): Boolean {
        if (lastCompletionDate == null) return true

        val today = getTodayDateString()
        return lastCompletionDate != today
    }

    fun getMilestoneAchieved(streakDays: Int): String? {
        return when {
            streakDays == 7 -> "7_day_streak"
            streakDays == 14 -> "14_day_streak"
            streakDays == 30 -> "30_day_streak"
            streakDays == 100 -> "100_day_streak"
            streakDays == 365 -> "365_day_streak"
            else -> null
        }
    }
}
