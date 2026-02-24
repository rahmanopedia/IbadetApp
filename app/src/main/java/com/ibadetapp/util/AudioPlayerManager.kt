package com.ibadetapp.util

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

/**
 * Manages ExoPlayer for Quran audio playback
 */
class AudioPlayerManager(private val context: Context) {

    private var player: ExoPlayer? = null

    private val _isPlaying = MutableLiveData(false)
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _currentPosition = MutableLiveData(0L)
    val currentPosition: LiveData<Long> = _currentPosition

    private val _duration = MutableLiveData(0L)
    val duration: LiveData<Long> = _duration

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        initializePlayer()
    }

    private fun initializePlayer() {
        if (player == null) {
            player = ExoPlayer.Builder(context).build().apply {
                addListener(PlayerEventListener())
            }
        }
    }

    fun playAudio(url: String) {
        player?.apply {
            val mediaItem = MediaItem.fromUri(url)
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }

    fun togglePlayPause() {
        player?.apply {
            if (isPlaying) {
                pause()
            } else {
                play()
            }
        }
    }

    fun pause() {
        player?.pause()
    }

    fun stop() {
        player?.stop()
    }

    fun seekTo(position: Long) {
        player?.seekTo(position)
    }

    fun release() {
        player?.release()
        player = null
    }

    private inner class PlayerEventListener : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_BUFFERING -> _isLoading.postValue(true)
                Player.STATE_READY -> _isLoading.postValue(false)
                Player.STATE_ENDED -> {}
                else -> {}
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _isPlaying.postValue(isPlaying)
            updatePosition()
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            player?.let {
                _duration.postValue(it.duration)
                _currentPosition.postValue(0)
            }
        }
    }

    private fun updatePosition() {
        player?.let {
            _currentPosition.postValue(it.currentPosition)
            if (it.isPlaying) {
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(
                    { updatePosition() },
                    100
                )
            }
        }
    }

    fun getCurrentPosition(): Long = player?.currentPosition ?: 0

    fun getDuration(): Long = player?.duration ?: 0

    fun isPlayerPlaying(): Boolean = player?.isPlaying ?: false
}
