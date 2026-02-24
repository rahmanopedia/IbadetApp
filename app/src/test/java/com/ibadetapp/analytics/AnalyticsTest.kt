package com.ibadetapp.analytics

import org.junit.Test
import org.junit.Assert.*

class AnalyticsTest {

    @Test
    fun `EventType enum should have all required events`() {
        val eventTypes = Analytics.EventType.values()
        assertTrue(eventTypes.isNotEmpty())

        val eventNames = eventTypes.map { it.name }
        assertTrue(eventNames.contains("APP_OPENED"))
        assertTrue(eventNames.contains("ZIKIR_COMPLETED"))
        assertTrue(eventNames.contains("BOOKMARK_ADDED"))
    }

    @Test
    fun `EventType should have zikirler events`() {
        val zikirEvents = listOf(
            "ZIKIR_STARTED",
            "ZIKIR_COMPLETED",
            "ZIKIR_ADDED",
            "ZIKIR_DELETED"
        )

        val eventNames = Analytics.EventType.values().map { it.name }
        zikirEvents.forEach { event ->
            assertTrue("Event $event should exist", eventNames.contains(event))
        }
    }

    @Test
    fun `EventType should have settings events`() {
        val settingEvents = listOf(
            "SETTINGS_OPENED",
            "THEME_CHANGED",
            "VIBRATION_TOGGLED"
        )

        val eventNames = Analytics.EventType.values().map { it.name }
        settingEvents.forEach { event ->
            assertTrue("Event $event should exist", eventNames.contains(event))
        }
    }

    @Test
    fun `getAnalyticsSummary should return non-empty string`() {
        val summary = Analytics.getAnalyticsSummary()
        assertFalse(summary.isEmpty())
        assertTrue(summary.contains("Analytics Engine"))
    }
}
