package com.ibadetapp.ui.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibadetapp.R
import com.ibadetapp.databinding.FragmentQuranListBinding

class QuranListFragment : Fragment() {

    private var _binding: FragmentQuranListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuranViewModel by viewModels()
    private lateinit var adapter: SurahAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuranListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearch()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = SurahAdapter { surah ->
            val action = QuranListFragmentDirections
                .actionNavigationQuranToSurahDetailFragment(surah.number, surah.turkishName)
            findNavController().navigate(action)
        }
        binding.rvSurahs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@QuranListFragment.adapter
        }
    }

    private fun setupSearch() {
        binding.etSearch.doAfterTextChanged { text ->
            viewModel.searchSurahs(text?.toString() ?: "")
        }
    }

    private fun observeViewModel() {
        viewModel.filteredSurahs.observe(viewLifecycleOwner) { surahs ->
            adapter.submitList(surahs)
            binding.tvEmptyState.visibility = if (surahs.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
