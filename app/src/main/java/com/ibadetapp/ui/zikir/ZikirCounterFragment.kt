package com.ibadetapp.ui.zikir

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ibadetapp.R
import com.ibadetapp.databinding.FragmentZikirCounterBinding

class ZikirCounterFragment : Fragment() {

    private var _binding: FragmentZikirCounterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ZikirViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZikirCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnCount.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.scale_click)
            binding.btnCount.startAnimation(animation)
            viewModel.increment()
        }

        binding.btnReset.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Sıfırla")
                .setMessage("Sayacı sıfırlamak istediğinize emin misiniz?")
                .setNegativeButton("İptal", null)
                .setPositiveButton("Sıfırla") { _, _ ->
                    viewModel.reset()
                }
                .show()
        }

        binding.btnVibration.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setVibrationEnabled(isChecked)
        }
    }

    private fun observeViewModel() {
        viewModel.currentZikir.observe(viewLifecycleOwner) { zikir ->
            zikir ?: return@observe
            binding.tvZikirArabic.text = zikir.arabicText
            binding.tvZikirTranslit.text = zikir.transliteration
            binding.tvZikirTurkish.text = zikir.turkishText
            binding.tvZikirCategory.text = zikir.category
            binding.tvTargetCount.text = "Hedef: ${zikir.targetCount}"
            updateProgressBar(0, zikir.targetCount)
        }

        viewModel.currentCount.observe(viewLifecycleOwner) { count ->
            binding.tvCurrentCount.text = count.toString()
            val target = viewModel.currentZikir.value?.targetCount ?: 33
            updateProgressBar(count, target)
        }

        viewModel.isCompleted.observe(viewLifecycleOwner) { isCompleted ->
            if (isCompleted) {
                showCompletionDialog()
            }
        }
    }

    private fun updateProgressBar(current: Int, target: Int) {
        binding.progressBar.max = target
        binding.progressBar.progress = current.coerceAtMost(target)
        val percent = if (target > 0) (current * 100 / target).coerceAtMost(100) else 0
        binding.tvProgress.text = "%$percent"
    }

    private fun showCompletionDialog() {
        val zikir = viewModel.currentZikir.value ?: return
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Tebrikler!")
            .setMessage("${zikir.transliteration} zikrini ${zikir.targetCount} kez tamamladınız.")
            .setIcon(R.drawable.ic_check_circle)
            .setPositiveButton("Tekrar") { _, _ ->
                viewModel.reset()
            }
            .setNegativeButton("Devam Et") { _, _ ->
                viewModel.continueAfterComplete()
            }
            .setCancelable(false)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
