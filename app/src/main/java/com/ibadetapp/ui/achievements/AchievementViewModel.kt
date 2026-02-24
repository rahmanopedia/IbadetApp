package com.ibadetapp.ui.achievements

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibadetapp.data.model.StreakEntry
import com.ibadetapp.data.model.UserAchievement
import com.ibadetapp.data.repository.AchievementRepository
import com.ibadetapp.data.repository.IbadetDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing achievements and streaks display
 */
class AchievementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AchievementRepository

    private val _currentStreak = MutableLiveData<Int>(0)
    val currentStreak: LiveData<Int> = _currentStreak

    private val _longestStreak = MutableLiveData<Int>(0)
    val longestStreak: LiveData<Int> = _longestStreak

    private val _unlockedAchievements = MutableLiveData<List<UserAchievement>>(emptyList())
    val unlockedAchievements: LiveData<List<UserAchievement>> = _unlockedAchievements

    private val _totalUnlocked = MutableLiveData<Int>(0)
    val totalUnlocked: LiveData<Int> = _totalUnlocked

    private val _recentStreaks = MutableLiveData<List<StreakEntry>>(emptyList())
    val recentStreaks: LiveData<List<StreakEntry>> = _recentStreaks

    init {
        val db = IbadetDatabase.getDatabase(application)
        repository = AchievementRepository(db.achievementDao(), db.streakDao())

        loadStreakData()
        loadAchievementData()
    }

    private fun loadStreakData() {
        viewModelScope.launch {
            // Load current streak
            val currentStreak = repository.getCurrentStreak()
            _currentStreak.value = currentStreak

            // Load longest streak
            val longest = repository.getLongestStreak()
            _longestStreak.value = longest

            // Check for milestone achievements
            repository.checkAndUnlockMilestones()
        }

        // Observe recent streaks
        viewModelScope.launch {
            repository.getRecentStreaks().collect { streaks ->
                _recentStreaks.value = streaks
            }
        }
    }

    private fun loadAchievementData() {
        viewModelScope.launch {
            // Initialize achievements if not done
            repository.initializeAchievements()
        }

        // Observe achievements
        viewModelScope.launch {
            repository.getUserAchievements().collect { achievements ->
                _unlockedAchievements.value = achievements
            }
        }

        // Observe total count
        viewModelScope.launch {
            repository.getTotalUnlockedCount().collect { count ->
                _totalUnlocked.value = count
            }
        }
    }

    fun recordDailyCompletion(
        zikirCompleted: Boolean = false,
        quranRead: Boolean = false
    ) {
        viewModelScope.launch {
            repository.recordDailyCompletion(
                zikirCompleted = zikirCompleted,
                quranRead = quranRead
            )
            loadStreakData()
        }
    }

    fun unlockAchievement(achievementId: String) {
        viewModelScope.launch {
            repository.unlockAchievement(achievementId)
        }
    }
}
