package com.ibadetapp.data.stats

import com.ibadetapp.data.model.ZikirSession
import java.util.Calendar
import java.util.Date

/**
 * Calculates advanced statistics from zikir sessions
 * Provides daily, weekly, monthly analytics
 */
object StatisticsCalculator {

    /**
     * Daily statistics
     */
    data class DailyStat(
        val date: String,
        val sessionsCount: Int,
        val totalCompletions: Int,
        val averageCount: Float
    )

    /**
     * Weekly statistics
     */
    data class WeeklyStat(
        val weekNumber: Int,
        val sessionsCount: Int,
        val totalCompletions: Int,
        val averageDaily: Float
    )

    /**
     * Monthly statistics
     */
    data class MonthlyStat(
        val month: String,
        val sessionsCount: Int,
        val totalCompletions: Int,
        val averageDaily: Float,
        val completionRate: Float
    )

    /**
     * Calculates daily statistics from sessions
     * @param sessions List of zikir sessions
     * @return List of daily statistics
     */
    fun calculateDailyStats(sessions: List<ZikirSession>): List<DailyStat> {
        val groupedByDate = sessions.groupBy { session ->
            val calendar = Calendar.getInstance().apply {
                timeInMillis = session.completedAt
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            String.format("%04d-%02d-%02d", year, month, day)
        }

        return groupedByDate.map { (date, daySessions) ->
            val count = daySessions.size
            val total = daySessions.sumOf { it.completedCount }
            DailyStat(
                date = date,
                sessionsCount = count,
                totalCompletions = total,
                averageCount = if (count > 0) total / count.toFloat() else 0f
            )
        }.sortedByDescending { it.date }
    }

    /**
     * Calculates weekly statistics
     * @param sessions List of zikir sessions
     * @return List of weekly statistics
     */
    fun calculateWeeklyStats(sessions: List<ZikirSession>): List<WeeklyStat> {
        val groupedByWeek = sessions.groupBy { session ->
            val calendar = Calendar.getInstance().apply {
                timeInMillis = session.completedAt
            }
            calendar.get(Calendar.WEEK_OF_YEAR)
        }

        return groupedByWeek.map { (week, weekSessions) ->
            val count = weekSessions.size
            val total = weekSessions.sumOf { it.completedCount }
            WeeklyStat(
                weekNumber = week,
                sessionsCount = count,
                totalCompletions = total,
                averageDaily = if (count > 0) total / 7f else 0f
            )
        }.sortedByDescending { it.weekNumber }
    }

    /**
     * Calculates monthly statistics
     * @param sessions List of zikir sessions
     * @return List of monthly statistics
     */
    fun calculateMonthlyStats(sessions: List<ZikirSession>): List<MonthlyStat> {
        val groupedByMonth = sessions.groupBy { session ->
            val calendar = Calendar.getInstance().apply {
                timeInMillis = session.completedAt
            }
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            String.format("%04d-%02d", year, month)
        }

        return groupedByMonth.map { (month, monthSessions) ->
            val count = monthSessions.size
            val total = monthSessions.sumOf { it.completedCount }
            val completed = monthSessions.count { it.isCompleted }
            MonthlyStat(
                month = month,
                sessionsCount = count,
                totalCompletions = total,
                averageDaily = if (count > 0) total / 30f else 0f,
                completionRate = if (count > 0) (completed / count.toFloat()) * 100 else 0f
            )
        }.sortedByDescending { it.month }
    }

    /**
     * Calculates overall statistics
     * @param sessions List of zikir sessions
     * @return Statistics summary
     */
    fun calculateOverallStats(sessions: List<ZikirSession>): Map<String, Any> {
        val completed = sessions.count { it.isCompleted }
        val total = sessions.size
        val totalCount = sessions.sumOf { it.completedCount }

        return mapOf(
            "totalSessions" to total,
            "completedSessions" to completed,
            "completionRate" to (if (total > 0) (completed / total.toFloat()) * 100 else 0f),
            "totalCompletions" to totalCount,
            "averagePerSession" to (if (total > 0) totalCount / total.toFloat() else 0f),
            "streak" to calculateCurrentStreak(sessions),
            "lastSession" to (sessions.maxByOrNull { it.completedAt }?.completedAt ?: 0L)
        )
    }

    /**
     * Calculates current completion streak
     * @param sessions List of zikir sessions
     * @return Current streak days
     */
    fun calculateCurrentStreak(sessions: List<ZikirSession>): Int {
        if (sessions.isEmpty()) return 0

        val sortedSessions = sessions.sortedByDescending { it.completedAt }
        val calendar = Calendar.getInstance()
        var streak = 0
        var lastDate: String? = null

        for (session in sortedSessions) {
            calendar.timeInMillis = session.completedAt
            val currentDate = String.format(
                "%04d-%02d-%02d",
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            if (lastDate == null) {
                lastDate = currentDate
                streak = 1
            } else if (currentDate != lastDate) {
                break
            }
        }

        return streak
    }
}
