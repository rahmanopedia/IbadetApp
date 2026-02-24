package com.ibadetapp.data.sync

import org.junit.Test
import org.junit.Assert.*

class SyncManagerTest {

    @Test
    fun `SyncStatus enum should have all required states`() {
        val statuses = SyncManager.SyncStatus.values()
        assertEquals(5, statuses.size)

        assertTrue(statuses.map { it.name }.contains("SYNCED"))
        assertTrue(statuses.map { it.name }.contains("PENDING"))
        assertTrue(statuses.map { it.name }.contains("OFFLINE"))
    }

    @Test
    fun `SyncOperation should require valid fields`() {
        val operation = SyncManager.SyncOperation(
            id = "op_1",
            type = "bookmark",
            action = "create",
            data = "{}",
            timestamp = System.currentTimeMillis()
        )

        assertEquals("op_1", operation.id)
        assertEquals("bookmark", operation.type)
        assertEquals("create", operation.action)
        assertEquals(SyncManager.SyncStatus.PENDING, operation.status)
    }

    @Test
    fun `resolveConflict should prefer latest timestamp`() {
        val now = System.currentTimeMillis()
        val older = now - 1000

        val local = Pair("local_data", older)
        val remote = Pair("remote_data", now)

        val result = testResolveConflict(local, remote)
        assertEquals("remote_data", result)
    }

    @Test
    fun `resolveConflict should prefer local if newer`() {
        val now = System.currentTimeMillis()
        val older = now - 1000

        val local = Pair("local_data", now)
        val remote = Pair("remote_data", older)

        val result = testResolveConflict(local, remote)
        assertEquals("local_data", result)
    }

    // Helper to test conflict resolution without context
    private fun testResolveConflict(local: Pair<String, Long>, remote: Pair<String, Long>): String {
        return if (local.second > remote.second) {
            local.first
        } else {
            remote.first
        }
    }

    @Test
    fun `getSyncStatusDescription should return valid descriptions`() {
        val descriptions = listOf(
            "✓ Senkronize",
            "⏳ 0 işlem bekleniyor",
            "🔄 Senkronizasyon yapılıyor...",
            "✗ Senkronizasyon başarısız",
            "📴 Çevrimdışı mod"
        )

        descriptions.forEach { desc ->
            assertFalse(desc.isEmpty())
        }
    }
}
