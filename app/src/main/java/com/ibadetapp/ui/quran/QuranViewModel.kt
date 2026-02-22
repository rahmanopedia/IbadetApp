package com.ibadetapp.ui.quran

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ibadetapp.data.model.BookmarkedAyah
import com.ibadetapp.data.model.Surah
import com.ibadetapp.data.repository.IbadetDatabase
import com.ibadetapp.data.repository.QuranRepository
import kotlinx.coroutines.launch

class QuranViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuranRepository
    val bookmarks: LiveData<List<BookmarkedAyah>>

    private val _surahs = MutableLiveData<List<Surah>>()
    val surahs: LiveData<List<Surah>> = _surahs

    private val _currentSurah = MutableLiveData<Surah?>()
    val currentSurah: LiveData<Surah?> = _currentSurah

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _searchQuery = MutableLiveData<String>("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _filteredSurahs = MutableLiveData<List<Surah>>()
    val filteredSurahs: LiveData<List<Surah>> = _filteredSurahs

    private val _isBookmarked = MutableLiveData<Boolean>()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    private var allSurahsList: List<Surah> = emptyList()

    init {
        val db = IbadetDatabase.getDatabase(application)
        repository = QuranRepository(application, db.bookmarkDao())
        bookmarks = repository.getAllBookmarks()
        loadSurahs()
    }

    private fun loadSurahs() {
        viewModelScope.launch {
            _isLoading.value = true
            allSurahsList = repository.getAllSurahs()
            _surahs.value = allSurahsList
            _filteredSurahs.value = allSurahsList
            _isLoading.value = false
        }
    }

    fun loadSurahDetail(surahNumber: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _currentSurah.value = repository.getSurahDetail(surahNumber)
            _isLoading.value = false
        }
    }

    fun searchSurahs(query: String) {
        _searchQuery.value = query
        if (query.isEmpty()) {
            _filteredSurahs.value = allSurahsList
        } else {
            _filteredSurahs.value = allSurahsList.filter { surah ->
                surah.turkishName.contains(query, ignoreCase = true) ||
                surah.englishName.contains(query, ignoreCase = true) ||
                surah.number.toString() == query
            }
        }
    }

    fun checkIfBookmarked(surahNumber: Int, ayahNumber: Int) {
        viewModelScope.launch {
            _isBookmarked.value = repository.isBookmarked(surahNumber, ayahNumber)
        }
    }

    fun toggleBookmark(
        surahNumber: Int,
        surahName: String,
        ayahNumber: Int,
        arabicText: String,
        turkishText: String
    ) {
        viewModelScope.launch {
            val isCurrentlyBookmarked = repository.isBookmarked(surahNumber, ayahNumber)
            if (isCurrentlyBookmarked) {
                repository.removeBookmark(surahNumber, ayahNumber)
                _isBookmarked.value = false
            } else {
                repository.addBookmark(
                    BookmarkedAyah(
                        surahNumber = surahNumber,
                        surahName = surahName,
                        ayahNumber = ayahNumber,
                        arabicText = arabicText,
                        turkishText = turkishText
                    )
                )
                _isBookmarked.value = true
            }
        }
    }
}
