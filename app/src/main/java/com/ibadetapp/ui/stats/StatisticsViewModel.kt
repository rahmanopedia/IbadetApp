package com.ibadetapp.ui.stats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibadetapp.data.repository.IbadetDatabase
import com.ibadetapp.data.repository.ZikirRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for statistics screen
 * Manages ibadet statistics calculations and display
 */
class StatisticsViewModel(application: Application) : AndroidViewModel(application) {

    private val zikirRepository: ZikirRepository
    val completedSessionCount: LiveData<Int>

    private val _bookmarksCount = MutableLiveData<Int>(0)
    val bookmarksCount: LiveData<Int> = _bookmarksCount

    private val _totalZikirCount = MutableLiveData<Int>(0)
    val totalZikirCount: LiveData<Int> = _totalZikirCount

    private val _statisticsText = MutableLiveData<String>("Veriler yükleniyor...")
    val statisticsText: LiveData<String> = _statisticsText

    init {
        val db = IbadetDatabase.getDatabase(application)
        zikirRepository = ZikirRepository(db.zikirDao())
        completedSessionCount = zikirRepository.completedSessionCount

        loadStatistics()
    }

    /**
     * Loads and calculates all statistics
     */
    private fun loadStatistics() {
        viewModelScope.launch {
            try {
                // Update statistics text
                updateStatisticsText()
            } catch (e: Exception) {
                _statisticsText.value = "İstatistikler yüklenirken hata oluştu"
            }
        }
    }

    /**
     * Sets bookmarks count
     * Should be called from BookmarksFragment or when bookmarks change
     */
    fun setBookmarksCount(count: Int) {
        _bookmarksCount.value = count
    }

    /**
     * Updates the statistics summary text
     */
    private fun updateStatisticsText() {
        val completed = completedSessionCount.value ?: 0
        val bookmarks = _bookmarksCount.value ?: 0
        val total = _totalZikirCount.value ?: 0

        val text = buildString {
            append("📊 İbadet İstatistikleri\n\n")
            append("✓ Tamamlanan Zikir Oturumları: $completed\n")
            append("🔖 Kaydedilen Ayetler: $bookmarks\n")
            append("🙏 Toplam Zikir Hedefi: $total")
        }

        _statisticsText.value = text
    }
}
