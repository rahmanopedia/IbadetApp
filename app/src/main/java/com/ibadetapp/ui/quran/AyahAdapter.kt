package com.ibadetapp.ui.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibadetapp.data.model.Ayah
import com.ibadetapp.databinding.ItemAyahBinding

class AyahAdapter(
    private val onBookmarkClick: (Ayah, String) -> Unit,
    private val onCopyClick: (Ayah) -> Unit
) : RecyclerView.Adapter<AyahAdapter.AyahViewHolder>() {

    private var ayahs: List<Ayah> = emptyList()
    private var surahName: String = ""
    private var surahNumber: Int = 0
    private var arabicFontSize: Float = 24f

    fun submitList(newAyahs: List<Ayah>, name: String, number: Int) {
        ayahs = newAyahs
        surahName = name
        surahNumber = number
        notifyDataSetChanged()
    }

    fun setArabicFontSize(size: Float) {
        arabicFontSize = size
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahViewHolder {
        val binding = ItemAyahBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AyahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AyahViewHolder, position: Int) {
        holder.bind(ayahs[position])
    }

    override fun getItemCount() = ayahs.size

    inner class AyahViewHolder(
        private val binding: ItemAyahBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ayah: Ayah) {
            binding.apply {
                tvAyahNumber.text = ayah.numberInSurah.toString()
                tvAyahArabic.text = ayah.arabicText
                tvAyahArabic.textSize = arabicFontSize
                tvAyahTurkish.text = ayah.turkishText

                btnBookmark.contentDescription = "$surahName - Ayet ${ayah.numberInSurah} - Işaretleme"
                btnBookmark.setOnClickListener {
                    onBookmarkClick(ayah, surahName)
                }

                btnCopy.contentDescription = "$surahName - Ayet ${ayah.numberInSurah} - Kopyala"
                btnCopy.setOnClickListener {
                    onCopyClick(ayah)
                }
            }
        }
    }
}
