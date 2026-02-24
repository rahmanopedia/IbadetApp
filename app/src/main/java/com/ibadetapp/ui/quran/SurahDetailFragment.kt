package com.ibadetapp.ui.quran

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibadetapp.R
import com.ibadetapp.databinding.FragmentSurahDetailBinding

class SurahDetailFragment : Fragment() {

    private var _binding: FragmentSurahDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuranViewModel by viewModels()
    private val args: SurahDetailFragmentArgs by navArgs()
    private lateinit var ayahAdapter: AyahAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurahDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        viewModel.loadSurahDetail(args.surahNumber)
        setupMenu()
    }

    private fun setupRecyclerView() {
        ayahAdapter = AyahAdapter(
            onBookmarkClick = { ayah, surahName ->
                viewModel.toggleBookmark(
                    surahNumber = args.surahNumber,
                    surahName = surahName,
                    ayahNumber = ayah.numberInSurah,
                    arabicText = ayah.arabicText,
                    turkishText = ayah.turkishText
                )
            },
            onCopyClick = { ayah ->
                requireContext().let { ctx ->
                    val text = "${ayah.arabicText}\n\n${ayah.turkishText}"
                    android.widget.Toast.makeText(ctx, "Kopyalandı", android.widget.Toast.LENGTH_SHORT).show()
                    val clipboard = ctx.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                    val clip = android.content.ClipData.newPlainText("Ayet", text)
                    clipboard.setPrimaryClip(clip)
                }
            }
        )
        binding.rvAyahs.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ayahAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.currentSurah.observe(viewLifecycleOwner) { surah ->
            surah ?: return@observe
            binding.tvBismillah.visibility = if (surah.number != 9 && surah.number != 1) {
                View.VISIBLE
            } else View.GONE
            ayahAdapter.submitList(surah.ayahs, surah.turkishName, args.surahNumber)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_surah_detail, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_font_size -> {
                        showFontSizeDialog()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun showFontSizeDialog() {
        val sizes = arrayOf("Küçük", "Orta", "Büyük", "Çok Büyük")
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Yazı Boyutu")
            .setItems(sizes) { _, which ->
                val fontSize = when (which) {
                    0 -> 18f
                    1 -> 22f
                    2 -> 28f
                    3 -> 34f
                    else -> 22f
                }
                ayahAdapter.setArabicFontSize(fontSize)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(surahNumber: Int) = SurahDetailFragment().apply {
            arguments = Bundle().apply {
                putInt("surahNumber", surahNumber)
            }
        }
    }
}
