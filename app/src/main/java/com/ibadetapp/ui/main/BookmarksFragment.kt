package com.ibadetapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibadetapp.R
import com.ibadetapp.databinding.FragmentBookmarksBinding
import com.ibadetapp.ui.quran.QuranListFragment
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

            if (bookmarks.isEmpty()) {
                binding.rvBookmarks.visibility = View.GONE
                binding.emptyStateBookmarks.apply {
                    visibility = View.VISIBLE
                    setEmptyState(
                        iconResId = R.drawable.ic_empty_bookmarks,
                        title = "Yer İmleri Boş",
                        description = "Henüz yer imi eklenmedi.\nKuran okurken ayetleri yer imlerine ekleyebilirsiniz.",
                        actionButtonText = "Kuran'ı Aç"
                    ) {
                        // Navigate to Quran fragment
                        parentFragmentManager.beginTransaction().apply {
                            replace(
                                com.google.android.material.R.id.navigation_host_fragment_activity_main,
                                QuranListFragment()
                            )
                            addToBackStack(null)
                            commit()
                        }
                    }
                }
            } else {
                binding.rvBookmarks.visibility = View.VISIBLE
                binding.emptyStateBookmarks.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
