package com.ibadetapp.ui.zikir

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.ibadetapp.R
import com.ibadetapp.data.model.Zikir
import com.ibadetapp.databinding.FragmentZikirListBinding

class ZikirListFragment : Fragment() {

    private var _binding: FragmentZikirListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ZikirViewModel by viewModels()
    private lateinit var adapter: ZikirAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZikirListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        setupFab()
        setupStats()
    }

    private fun setupRecyclerView() {
        adapter = ZikirAdapter(
            onZikirClick = { zikir ->
                viewModel.selectZikir(zikir)
                findNavController().navigate(R.id.action_navigation_zikir_to_zikirCounterFragment)
            },
            onDeleteClick = { zikir ->
                if (zikir.isCustom) {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Zikir Sil")
                        .setMessage("\"${zikir.transliteration}\" zikrini silmek istediğinize emin misiniz?")
                        .setNegativeButton("İptal", null)
                        .setPositiveButton("Sil") { _, _ ->
                            viewModel.deleteZikir(zikir)
                        }
                        .show()
                }
            }
        )
        binding.rvZikirler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ZikirListFragment.adapter
        }
    }

    private fun observeViewModel() {
        viewModel.allZikirler.observe(viewLifecycleOwner) { zikirler ->
            adapter.submitList(zikirler)
        }
    }

    private fun setupStats() {
        viewModel.completedSessionCount.observe(viewLifecycleOwner) { count ->
            binding.tvCompletedCount.text = "Tamamlanan: $count"
        }
    }

    private fun setupFab() {
        binding.fabAddZikir.setOnClickListener {
            showAddZikirDialog()
        }
    }

    private fun showAddZikirDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_zikir, null)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Özel Zikir Ekle")
            .setView(dialogView)
            .setNegativeButton("İptal", null)
            .setPositiveButton("Ekle") { _, _ ->
                val arabicText = dialogView.findViewById<TextInputEditText>(R.id.et_arabic_text)
                    ?.text?.toString()?.trim() ?: ""
                val turkishText = dialogView.findViewById<TextInputEditText>(R.id.et_turkish_text)
                    ?.text?.toString()?.trim() ?: ""
                val transliteration = dialogView.findViewById<TextInputEditText>(R.id.et_transliteration)
                    ?.text?.toString()?.trim() ?: ""
                val targetCount = dialogView.findViewById<TextInputEditText>(R.id.et_target_count)
                    ?.text?.toString()?.toIntOrNull() ?: 33

                if (transliteration.isNotEmpty()) {
                    viewModel.addCustomZikir(
                        Zikir(
                            arabicText = arabicText,
                            turkishText = turkishText,
                            transliteration = transliteration,
                            targetCount = targetCount,
                            isCustom = true,
                            category = "Özel"
                        )
                    )
                }
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
