package com.ibadetapp.ui.zikir

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibadetapp.data.model.Zikir
import com.ibadetapp.databinding.ItemZikirBinding

class ZikirAdapter(
    private val onZikirClick: (Zikir) -> Unit,
    private val onDeleteClick: (Zikir) -> Unit
) : ListAdapter<Zikir, ZikirAdapter.ZikirViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZikirViewHolder {
        val binding = ItemZikirBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ZikirViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZikirViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ZikirViewHolder(
        private val binding: ItemZikirBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(zikir: Zikir) {
            binding.apply {
                tvZikirTranslit.text = zikir.transliteration
                tvZikirArabic.text = zikir.arabicText
                tvZikirTurkish.text = zikir.turkishText
                tvZikirTarget.text = zikir.targetCount.toString()
                tvZikirCategory.text = zikir.category

                // Progress bar ayarla
                val progress = if (zikir.targetCount > 0) {
                    ((zikir.currentCount.toFloat() / zikir.targetCount) * 100).toInt()
                } else 0
                
                progressBar.progress = progress
                tvProgressPercent.text = "$progress%"

                btnDelete.visibility = if (zikir.isCustom) View.VISIBLE else View.GONE
                btnDelete.setOnClickListener { onDeleteClick(zikir) }

                root.setOnClickListener { onZikirClick(zikir) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Zikir>() {
        override fun areItemsTheSame(oldItem: Zikir, newItem: Zikir) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Zikir, newItem: Zikir) = oldItem == newItem
    }
}