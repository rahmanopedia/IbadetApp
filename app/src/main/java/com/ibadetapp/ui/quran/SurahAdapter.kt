package com.ibadetapp.ui.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibadetapp.data.model.Surah
import com.ibadetapp.databinding.ItemSurahBinding

class SurahAdapter(
    private val onSurahClick: (Surah) -> Unit
) : ListAdapter<Surah, SurahAdapter.SurahViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val binding = ItemSurahBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SurahViewHolder(
        private val binding: ItemSurahBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(surah: Surah) {
            binding.apply {
                tvSurahNumber.text = surah.number.toString()
                tvSurahName.text = surah.turkishName
                tvSurahArabicName.text = surah.name
                tvSurahInfo.text = "${surah.numberOfAyahs} Ayet • ${
                    if (surah.revelationType == "Meccan") "Mekki" else "Medeni"
                }"

                root.contentDescription = "${surah.turkishName} (${surah.name}) - ${surah.numberOfAyahs} ayet"
                root.setOnClickListener { onSurahClick(surah) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Surah>() {
        override fun areItemsTheSame(oldItem: Surah, newItem: Surah) =
            oldItem.number == newItem.number

        override fun areContentsTheSame(oldItem: Surah, newItem: Surah) =
            oldItem == newItem
    }
}
