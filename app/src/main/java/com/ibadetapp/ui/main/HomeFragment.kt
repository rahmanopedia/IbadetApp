package com.ibadetapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ibadetapp.R
import com.ibadetapp.data.model.DailyVersesList
import com.ibadetapp.databinding.FragmentHomeBinding
import com.ibadetapp.util.PrayerTimeUtil
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDateDisplay()
        setupQuickActions()
        setupDailyVerse()
    }

    private fun setupDateDisplay() {
        val calendar = Calendar.getInstance()

        // Miladi tarih
        val dateFormat = SimpleDateFormat("dd MMMM yyyy, EEEE", Locale("tr"))
        binding.tvMiladiDate.text = dateFormat.format(calendar.time)

        // Hicri tarih (yaklaşık)
        binding.tvHicriDate.text = PrayerTimeUtil.getHijriDate()
    }

    private fun setupQuickActions() {
        binding.cardQuran.setOnClickListener {
            findNavController().navigate(R.id.navigation_quran)
        }

        binding.cardZikir.setOnClickListener {
            findNavController().navigate(R.id.navigation_zikir)
        }

        binding.cardBookmarks.setOnClickListener {
            findNavController().navigate(R.id.navigation_bookmarks)
        }
    }

    private fun setupDailyVerse() {
        // Get today's verse (rotates daily)
        val calendar = Calendar.getInstance()
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val dailyVerse = DailyVersesList.getVerseForDay(dayOfYear)

        binding.tvDailyVerseArabic.text = dailyVerse.arabicText
        binding.tvDailyVerseTurkish.text = dailyVerse.turkishText
        binding.tvDailyVerseRef.text = "${dailyVerse.surahNameTurkish}, ${dailyVerse.ayahNumber}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
