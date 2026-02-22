package com.ibadetapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibadetapp.databinding.FragmentBookmarksBinding
import com.ibadetapp.ui.quran.QuranViewModel

class BookmarksFragment : Fragment() {

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuranViewModel by viewModels()
    private lateinit var adapter: BookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = BookmarkAdapter(
            onDeleteClick = { bookmark ->
                viewModel.toggleBookmark(
                    surahNumber = bookmark.surahNumber,
                    surahName = bookmark.surahName,
                    ayahNumber = bookmark.ayahNumber,
                    arabicText = bookmark.arabicText,
                    turkishText = bookmark.turkishText
                )
            }
        )
        binding.rvBookmarks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@BookmarksFragment.adapter
        }
    }

    private fun observeViewModel() {
        viewModel.bookmarks.observe(viewLifecycleOwner) { bookmarks ->
            adapter.submitList(bookmarks)
            binding.tvEmptyState.visibility = if (bookmarks.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
