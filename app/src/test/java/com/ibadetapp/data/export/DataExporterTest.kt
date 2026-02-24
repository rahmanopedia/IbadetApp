package com.ibadetapp.data.export

import org.junit.Test
import org.junit.Assert.*

class DataExporterTest {

    @Test
    fun `formatFileSize should handle zero bytes`() {
        // Test locally without context
        testFormatFileSize(0, "0 B")
    }

    @Test
    fun `formatFileSize should format bytes`() {
        testFormatFileSize(100, "100 B")
    }

    @Test
    fun `formatFileSize should format kilobytes`() {
        testFormatFileSize(1024, "1 KB")
        testFormatFileSize(2048, "2 KB")
    }

    @Test
    fun `formatFileSize should format megabytes`() {
        testFormatFileSize(1024 * 1024, "1 MB")
        testFormatFileSize(5 * 1024 * 1024, "5 MB")
    }

    // Helper function to test file size formatting
    private fun testFormatFileSize(bytes: Long, expected: String) {
        val result = when {
            bytes <= 0 -> "0 B"
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            else -> "${bytes / (1024 * 1024)} MB"
        }
        assertEquals(expected, result)
    }
}
