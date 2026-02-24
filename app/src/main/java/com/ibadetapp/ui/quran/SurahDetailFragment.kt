package com.ibadetapp.ui.quran

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.ibadetapp.R
import com.ibadetapp.data.model.RecitationList
import com.ibadetapp.databinding.FragmentSurahDetailBinding

class SurahDetailFragment : Fragment() {

    private var _binding: FragmentSurahDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: QuranViewModel by viewModels()
    private val audioPlayerViewModel: AudioPlayerViewModel by viewModels()
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
        setupAudioPlayer()
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

    private fun setupAudioPlayer() {
        val audioPlayerView = binding.audioPlayerView
        val spinnerReciters = audioPlayerView.findViewById<Spinner>(R.id.spinner_reciters)
        val btnPlayPause = audioPlayerView.findViewById<MaterialButton>(R.id.btn_play_pause)
        val seekbarProgress = audioPlayerView.findViewById<android.widget.SeekBar>(R.id.seekbar_progress)
        val tvCurrentTime = audioPlayerView.findViewById<android.widget.TextView>(R.id.tv_current_time)
        val tvDuration = audioPlayerView.findViewById<android.widget.TextView>(R.id.tv_duration)
        val progressLoading = audioPlayerView.findViewById<android.widget.ProgressBar>(R.id.progress_loading)

        // Setup reciter spinner
        val reciterNames = RecitationList.reciters.map { it.name }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, reciterNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerReciters.adapter = adapter

        spinnerReciters.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val reciterId = RecitationList.reciters[position].id
                audioPlayerViewModel.setReciter(reciterId)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        }

        // Setup play/pause button
        btnPlayPause.setOnClickListener {
            if (audioPlayerViewModel.isPlaying.value == true) {
                audioPlayerViewModel.pause()
            } else {
                audioPlayerViewModel.playSurahAudio(args.surahNumber)
            }
        }

        // Setup seekbar
        seekbarProgress.setOnSeekBarChangeListener(object : android.widget.SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: android.widget.SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    audioPlayerViewModel.seekTo(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: android.widget.SeekBar) {}
            override fun onStopTrackingTouch(seekBar: android.widget.SeekBar) {}
        })

        // Observe audio player state
        audioPlayerViewModel.isPlaying.observe(viewLifecycleOwner) { isPlaying ->
            btnPlayPause.text = if (isPlaying) "⏸" else "▶"
        }

        audioPlayerViewModel.currentPosition.observe(viewLifecycleOwner) { position ->
            seekbarProgress.progress = position.toInt()
            tvCurrentTime.text = audioPlayerViewModel.formatTime(position)
        }

        audioPlayerViewModel.duration.observe(viewLifecycleOwner) { duration ->
            seekbarProgress.max = duration.toInt()
            tvDuration.text = audioPlayerViewModel.formatTime(duration)
        }

        audioPlayerViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
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
