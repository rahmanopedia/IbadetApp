package com.ibadetapp.data.repository

import androidx.lifecycle.LiveData
import com.ibadetapp.data.model.Zikir
import com.ibadetapp.data.model.ZikirSession

class ZikirRepository(private val zikirDao: ZikirDao) {

    val allZikirler: LiveData<List<Zikir>> = zikirDao.getAllZikirler()
    val recentSessions: LiveData<List<ZikirSession>> = zikirDao.getRecentSessions()
    val completedSessionCount: LiveData<Int> = zikirDao.getCompletedSessionCount()

    suspend fun insert(zikir: Zikir): Long = zikirDao.insert(zikir)

    suspend fun update(zikir: Zikir) = zikirDao.update(zikir)

    suspend fun delete(zikir: Zikir) = zikirDao.delete(zikir)

    suspend fun updateCount(id: Int, count: Int) = zikirDao.updateCount(id, count)

    suspend fun resetCount(id: Int) = zikirDao.resetCount(id)

    suspend fun insertSession(session: ZikirSession): Long = zikirDao.insertSession(session)

    suspend fun getZikirById(id: Int): Zikir? = zikirDao.getZikirById(id)
}
