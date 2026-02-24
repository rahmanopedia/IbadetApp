package com.ibadetapp.ui.achievements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ibadetapp.R
import com.ibadetapp.databinding.FragmentAchievementsBinding

/**
 * Fragment for displaying achievements and streak progress
 */
class AchievementsFragment : Fragment() {

    private var _binding: FragmentAchievementsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AchievementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStreakDisplay()
        setupAchievementsDisplay()
    }

    private fun setupStreakDisplay() {
        viewModel.currentStreak.observe(viewLifecycleOwner) { streak ->
            binding.tvCurrentStreak.text = streak.toString()
            binding.tvStreakLabel.text = if (streak == 1) "Gün" else "Gün"
        }

        viewModel.longestStreak.observe(viewLifecycleOwner) { longest ->
            binding.tvLongestStreak.text = longest.toString()
        }
    }

    private fun setupAchievementsDisplay() {
        viewModel.totalUnlocked.observe(viewLifecycleOwner) { count ->
            binding.tvAchievementCount.text = "$count / 10"
        }

        viewModel.unlockedAchievements.observe(viewLifecycleOwner) { achievements ->
            binding.tvUnlockedAchievements.text = buildString {
                append("Açılmış Başarılar:\n")
                achievements.forEach { achievement ->
                    append("✓ ${achievement.achievementId}\n")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
