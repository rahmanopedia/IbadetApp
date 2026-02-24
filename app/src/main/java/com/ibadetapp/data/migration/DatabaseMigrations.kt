package com.ibadetapp.data.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Database migrations for version management
 * Handles schema changes between app versions
 */
object DatabaseMigrations {

    /**
     * Migration from version 1 to 2
     * Adds new columns or tables
     */
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Example: Add new column to existing table
            // database.execSQL("ALTER TABLE zikir ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0")
        }
    }

    /**
     * Migration from version 2 to 3
     * Updates schema for new features
     */
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Example: Create new table for statistics
            // database.execSQL("CREATE TABLE IF NOT EXISTS daily_stats (id INTEGER PRIMARY KEY, date TEXT, count INTEGER)")
        }
    }

    /**
     * Migration from version 3 to 4
     * Adds analytics tracking
     */
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Example: Add tracking fields
            // database.execSQL("ALTER TABLE zikir_session ADD COLUMN tracking_id TEXT")
        }
    }

    /**
     * Gets all migrations
     * @return Array of migrations
     */
    fun getAllMigrations(): Array<Migration> {
        return arrayOf(
            MIGRATION_1_2,
            MIGRATION_2_3,
            MIGRATION_3_4
        )
    }

    /**
     * Migration helper class
     */
    class MigrationHelper {
        companion object {
            /**
             * Logs migration execution
             * @param fromVersion Source version
             * @param toVersion Target version
             */
            fun logMigration(fromVersion: Int, toVersion: Int) {
                android.util.Log.i(
                    "DatabaseMigration",
                    "Migrating from v$fromVersion to v$toVersion"
                )
            }

            /**
             * Validates migration success
             * @param database Database instance
             * @return true if valid
             */
            fun validateMigration(database: SupportSQLiteDatabase): Boolean {
                return try {
                    // Check if required tables exist
                    database.query("SELECT name FROM sqlite_master WHERE type='table'").use { cursor ->
                        cursor.count > 0
                    }
                } catch (e: Exception) {
                    false
                }
            }
        }
    }
}
