package com.ibadetapp.ui.quran

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.ibadetapp.data.model.RecitationList
import com.ibadetapp.util.AudioPlayerManager

/**
 * ViewModel for managing Quran audio playback
 */
class AudioPlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val audioPlayerManager = AudioPlayerManager(application)

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _currentPosition = MutableLiveData(0L)
    val currentPosition: LiveData<Long> = _currentPosition

    private val _duration = MutableLiveData(0L)
    val duration: LiveData<Long> = _duration

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _currentSurah = MutableLiveData(0)
    val currentSurah: LiveData<Int> = _currentSurah

    private val _currentReciter = MutableLiveData("")
    val currentReciter: LiveData<String> = _currentReciter

    init {
        observePlayerState()
        loadPreferences()
    }

    private fun observePlayerState() {
        audioPlayerManager.isPlaying.observeForever {
            _isPlaying.value = it
        }
        audioPlayerManager.currentPosition.observeForever {
            _currentPosition.value = it
        }
        audioPlayerManager.duration.observeForever {
            _duration.value = it
        }
        audioPlayerManager.isLoading.observeForever {
            _isLoading.value = it
        }
    }

    private fun loadPreferences() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(getApplication())
        val savedReciter = prefs.getString("quran_reciter", "abdulbasit") ?: "abdulbasit"
        _currentReciter.value = savedReciter
    }

    fun playSurahAudio(surahNumber: Int) {
        val reciterId = _currentReciter.value ?: "abdulbasit"
        val url = RecitationList.getSurahUrl(reciterId, surahNumber)
        _currentSurah.value = surahNumber
        audioPlayerManager.playAudio(url)
        savePreferences()
    }

    fun togglePlayPause() {
        audioPlayerManager.togglePlayPause()
    }

    fun pause() {
        audioPlayerManager.pause()
    }

    fun seekTo(position: Long) {
        audioPlayerManager.seekTo(position)
    }

    fun setReciter(reciterId: String) {
        _currentReciter.value = reciterId
        // If audio is currently playing, reload with new reciter
        _currentSurah.value?.let {
            playSurahAudio(it)
        }
    }

    fun formatTime(milliseconds: Long): String {
        val totalSeconds = (milliseconds / 1000).toInt()
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    private fun savePreferences() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(getApplication())
        prefs.edit().apply {
            putString("quran_reciter", _currentReciter.value ?: "abdulbasit")
            apply()
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayerManager.release()
    }
}
