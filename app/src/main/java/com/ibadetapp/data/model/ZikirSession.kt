package com.ibadetapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zikir_session_table")
data class ZikirSession(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val zikirId: Int,
    val zikirName: String,
    val targetCount: Int,
    val completedCount: Int,
    val completedAt: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
)
