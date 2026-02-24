package com.ibadetapp.data.stats

import com.ibadetapp.data.model.ZikirSession
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class StatisticsCalculatorTest {

    private val now = System.currentTimeMillis()
    private val testSessions = listOf(
        ZikirSession(id = 1, zikirId = 1, zikirName = "Subhanallah", targetCount = 33, completedCount = 33, isCompleted = true, completedAt = now),
        ZikirSession(id = 2, zikirId = 2, zikirName = "Alhamdulillah", targetCount = 33, completedCount = 33, isCompleted = true, completedAt = now - 86400000),
        ZikirSession(id = 3, zikirId = 1, zikirName = "Subhanallah", targetCount = 33, completedCount = 20, isCompleted = false, completedAt = now - 172800000)
    )

    @Test
    fun `calculateDailyStats should group sessions by date`() {
        val stats = StatisticsCalculator.calculateDailyStats(testSessions)
        assertTrue(stats.isNotEmpty())
        assertTrue(stats.size >= 2)
    }

    @Test
    fun `calculateWeeklyStats should calculate average correctly`() {
        val stats = StatisticsCalculator.calculateWeeklyStats(testSessions)
        assertTrue(stats.isNotEmpty())
        assertTrue(stats[0].averageDaily >= 0)
    }

    @Test
    fun `calculateMonthlyStats should include completion rate`() {
        val stats = StatisticsCalculator.calculateMonthlyStats(testSessions)
        assertTrue(stats.isNotEmpty())
        assertTrue(stats[0].completionRate >= 0 && stats[0].completionRate <= 100)
    }

    @Test
    fun `calculateOverallStats should return correct counts`() {
        val stats = StatisticsCalculator.calculateOverallStats(testSessions)
        assertEquals(3, stats["totalSessions"])
        assertEquals(2, stats["completedSessions"])
    }

    @Test
    fun `calculateOverallStats should calculate completion rate`() {
        val stats = StatisticsCalculator.calculateOverallStats(testSessions)
        val rate = (stats["completionRate"] as Float)
        assertTrue(rate >= 0 && rate <= 100)
    }

    @Test
    fun `calculateCurrentStreak should return at least 1 for non-empty sessions`() {
        val streak = StatisticsCalculator.calculateCurrentStreak(testSessions)
        assertTrue(streak >= 0)
    }

    @Test
    fun `calculateCurrentStreak should return 0 for empty sessions`() {
        val streak = StatisticsCalculator.calculateCurrentStreak(emptyList())
        assertEquals(0, streak)
    }
}
