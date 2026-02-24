package com.ibadetapp.ui.zikir

import android.app.Application
import android.os.Vibrator
import androidx.core.content.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibadetapp.data.model.Zikir
import com.ibadetapp.data.model.ZikirSession
import com.ibadetapp.data.repository.IbadetDatabase
import com.ibadetapp.data.repository.ZikirRepository
import kotlinx.coroutines.launch

class ZikirViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ZikirRepository
    val allZikirler: LiveData<List<Zikir>>
    val recentSessions: LiveData<List<ZikirSession>>
    val completedSessionCount: LiveData<Int>

    private val _currentZikir = MutableLiveData<Zikir?>()
    val currentZikir: LiveData<Zikir?> = _currentZikir

    private val _currentCount = MutableLiveData<Int>(0)
    val currentCount: LiveData<Int> = _currentCount

    private val _isCompleted = MutableLiveData<Boolean>(false)
    val isCompleted: LiveData<Boolean> = _isCompleted

    private val _totalCount = MutableLiveData<Int>(0)
    val totalCount: LiveData<Int> = _totalCount

    private var vibrator: Vibrator? = null
    private var isVibrationEnabled = true

    init {
        val db = IbadetDatabase.getDatabase(application)
        repository = ZikirRepository(db.zikirDao())
        allZikirler = repository.allZikirler
        recentSessions = repository.recentSessions
        completedSessionCount = repository.completedSessionCount
        vibrator = application.getSystemService()
        
        // Calculate total target count
        viewModelScope.launch {
            allZikirler.value?.let { zikirler ->
                _totalCount.value = zikirler.sumOf { it.targetCount }
            }
        }
    }

    fun selectZikir(zikir: Zikir) {
        _currentZikir.value = zikir
        _currentCount.value = 0
        _isCompleted.value = false
    }

    fun increment() {
        val zikir = _currentZikir.value ?: return
        val current = _currentCount.value ?: 0
        val newCount = current + 1
        _currentCount.value = newCount

        if (isVibrationEnabled) {
            vibrate(30)
        }

        if (newCount >= zikir.targetCount && !(_isCompleted.value ?: false)) {
            _isCompleted.value = true
            vibrate(200)
            saveSession(zikir, newCount, true)
        }
    }

    fun reset() {
        _currentCount.value = 0
        _isCompleted.value = false
    }

    fun continueAfterComplete() {
        _isCompleted.value = false
    }

    fun setVibrationEnabled(enabled: Boolean) {
        isVibrationEnabled = enabled
    }

    /**
     * Adds a custom zikir with validation
     * @param zikir The zikir to add
     * @return true if valid and added, false if validation failed
     */
    fun addCustomZikir(zikir: Zikir): Boolean {
        // Validate input
        if (!isValidCustomZikir(zikir)) {
            return false
        }

        viewModelScope.launch {
            repository.insert(zikir)
        }
        return true
    }

    /**
     * Validates custom zikir input
     * @param zikir The zikir to validate
     * @return true if valid, false otherwise
     */
    private fun isValidCustomZikir(zikir: Zikir): Boolean {
        return zikir.arabicText.isNotBlank() &&
                zikir.turkishText.isNotBlank() &&
                zikir.transliteration.isNotBlank() &&
                zikir.targetCount > 0 &&
                zikir.targetCount <= MAX_ZIKIR_TARGET_COUNT &&
                zikir.category.isNotBlank()
    }

    fun deleteZikir(zikir: Zikir) {
        viewModelScope.launch {
            repository.delete(zikir)
        }
    }

    private fun saveSession(zikir: Zikir, count: Int, completed: Boolean) {
        viewModelScope.launch {
            repository.insertSession(
                ZikirSession(
                    zikirId = zikir.id,
                    zikirName = zikir.transliteration,
                    targetCount = zikir.targetCount,
                    completedCount = count,
                    isCompleted = completed
                )
            )
        }
    }

    @Suppress("DEPRECATION")
    private fun vibrate(milliseconds: Long) {
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator?.vibrate(
                    android.os.VibrationEffect.createOneShot(
                        milliseconds,
                        android.os.VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator?.vibrate(milliseconds)
            }
        } catch (e: Exception) {
            // Vibration not available
        }
    }

    companion object {
        private const val MAX_ZIKIR_TARGET_COUNT = 10000
    }
}