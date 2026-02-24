package com.ibadetapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibadetapp.data.model.BookmarkedAyah
import com.ibadetapp.databinding.ItemBookmarkBinding
import java.text.SimpleDateFormat
import java.util.*

class BookmarkAdapter(
    private val onDeleteClick: (BookmarkedAyah) -> Unit
) : ListAdapter<BookmarkedAyah, BookmarkAdapter.BookmarkViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemBookmarkBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookmarkViewHolder(
        private val binding: ItemBookmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(bookmark: BookmarkedAyah) {
            binding.apply {
                tvSurahName.text = "${bookmark.surahName} - ${bookmark.ayahNumber}. Ayet"
                tvArabicText.text = bookmark.arabicText
                tvTurkishText.text = bookmark.turkishText

                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale("tr"))
                tvSavedDate.text = dateFormat.format(Date(bookmark.savedAt))

                btnDelete.contentDescription = "${bookmark.surahName} - Ayet ${bookmark.ayahNumber} - Sil"
                btnDelete.setOnClickListener { onDeleteClick(bookmark) }

                val desc = "${bookmark.surahName}, Ayet ${bookmark.ayahNumber}, ${tvSavedDate.text} tarihinde kaydedildi"
                root.contentDescription = desc
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BookmarkedAyah>() {
        override fun areItemsTheSame(oldItem: BookmarkedAyah, newItem: BookmarkedAyah) =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: BookmarkedAyah, newItem: BookmarkedAyah) =
            oldItem == newItem
    }
}
