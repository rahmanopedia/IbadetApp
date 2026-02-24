package com.ibadetapp.ui.quran

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibadetapp.data.model.Surah

/**
 * ViewPager2 adapter for smooth Surah navigation
 * Enables swiping through Surahs without reloading data
 */
class SurahPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val surahs: List<Surah>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = surahs.size

    override fun createFragment(position: Int): Fragment {
        return SurahDetailFragment.newInstance(surahs[position].number)
    }

    /**
     * Updates the list of surahs and notifies adapter of changes
     * @param newSurahs The new list of surahs
     */
    fun updateSurahs(newSurahs: List<Surah>) {
        notifyItemRangeChanged(0, itemCount)
    }

    /**
     * Gets the surah at the specified position
     * @param position Position in the list
     * @return Surah at position, or null if out of bounds
     */
    fun getSurahAtPosition(position: Int): Surah? {
        return if (position in 0 until surahs.size) {
            surahs[position]
        } else {
            null
        }
    }

    /**
     * Finds the position of a surah by its number
     * @param surahNumber The surah number to find
     * @return Position of surah, or -1 if not found
     */
    fun getPositionBySurahNumber(surahNumber: Int): Int {
        return surahs.indexOfFirst { it.number == surahNumber }
    }
}
