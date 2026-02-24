package com.ibadetapp.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibadetapp.databinding.FragmentStatisticsBinding

/**
 * Fragment displaying user's ibadet statistics
 * Shows daily/monthly zikir completion stats, bookmarks count, etc.
 */
class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding
    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        // Completed sessions count
        viewModel.completedSessionCount.observe(viewLifecycleOwner) { count ->
            binding.tvCompletedSessions.text = count.toString()
        }

        // Total bookmarks count
        viewModel.bookmarksCount.observe(viewLifecycleOwner) { count ->
            binding.tvBookmarksCount.text = count.toString()
        }

        // Total zikirler
        viewModel.totalZikirCount.observe(viewLifecycleOwner) { count ->
            binding.tvTotalZikir.text = count.toString()
        }

        // Statistics text
        viewModel.statisticsText.observe(viewLifecycleOwner) { text ->
            binding.tvStatisticsText.text = text
        }
    }

    companion object {
        fun newInstance() = StatisticsFragment()
    }
}
