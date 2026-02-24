package com.ibadetapp.data.model

/**
 * Represents a daily verse to be displayed
 * Rotates daily throughout the year
 */
data class DailyVerse(
    val id: Int,
    val surahNumber: Int,
    val surahNameTurkish: String,
    val ayahNumber: Int,
    val arabicText: String,
    val turkishText: String,
    val theme: String = "Wisdom" // Topic/theme of the verse
)

// Curated list of 365 verses for the year
object DailyVersesList {
    val verses = listOf(
        DailyVerse(1, 1, "Fatiha", 1, "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ", "Allah'ın adıyla başlarım, O'nun rahmeti ve lütfu ile", "Opening"),
        DailyVerse(2, 2, "Bakara", 286, "لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا", "Allah hiç kimseyi kendisinin gücü dışında bir şeyle yükümlü kılmaz", "Comfort"),
        DailyVerse(3, 3, "Ali İmran", 26, "قُلِ اللَّهُمَّ مَالِكَ الْمُلْكِ تُؤْتِي الْمُلْكَ مَن تَشَاءُ", "De ki: Ey Allah, mülkün sahibi! Mülkü dilediğine verirsin", "Authority"),
        DailyVerse(4, 4, "Nisa", 1, "يَا أَيُّهَا النَّاسُ اتَّقُوا رَبَّكُمُ الَّذِي خَلَقَكُم", "Ey insanlar! Sizi yaratan Rabbinizden sakının", "Unity"),
        DailyVerse(5, 5, "Maide", 3, "الْيَوْمَ أَكْمَلْتُ لَكُمْ دِينَكُمْ", "Bu gün sizin dininizi tamamladım", "Completion"),
        DailyVerse(6, 7, "Araf", 180, "وَلِلَّهِ الْأَسْمَاءُ الْحُسْنَىٰ فَادْعُوهُ بِهَا", "Allah'ın en güzel isimleri vardır, O'na bunlarla dua edin", "Names"),
        DailyVerse(7, 10, "Yunus", 57, "يَا أَيُّهَا النَّاسُ قَدْ جَاءَتْكُم مَّوْعِظَةٌ مِّن رَّبِّكُمْ", "Ey insanlar! Size Rabbinizden bir öğüt geldi", "Guidance"),
        DailyVerse(8, 13, "Rad", 28, "الَّذِينَ آمَنُوا وَتَطْمَئِنُّ قُلُوبُهُم بِذِكْرِ اللَّهِ", "Inananlardır ki kalpleri Allah'ın anısıyla huzur bulur", "Peace"),
        DailyVerse(9, 16, "Nahl", 97, "مَن عَمِلَ صَالِحًا مِّن ذَكَرٍ أَوْ أُنثَىٰ وَهُوَ مُؤْمِنٌ", "Kim iyi ameller yaparsa, erkek veya kadın, inanmış ise", "Reward"),
        DailyVerse(10, 20, "Taha", 114, "وَقُل رَّبِّ زِدْنِي عِلْمًا", "De ki: Ey Rabbim! Bana ilim arttır", "Knowledge"),
        DailyVerse(11, 36, "Yasin", 82, "سُبْحَانَ الَّذِي خَلَقَ الْأَزْوَاجَ كُلَّهَا", "Tüm çiftleri yaratan (Allah) subhanallah", "Creation"),
        DailyVerse(12, 67, "Talak", 2, "وَمَن يَتَّقِ اللَّهَ يَجْعَل لَّهُ مَخْرَجًا", "Kim Allah'tan sakınırsa, O'na bir çıkış yolu gösterir", "Trust"),
        DailyVerse(13, 73, "Muzzemmil", 8, "فَاقْرَأْ مَا تَيَسَّرَ مِنَ الْقُرْآنِ", "Kuran'dan sana kolaylaştırılanını oku", "Ease"),
        DailyVerse(14, 94, "Alak", 1, "اقْرَأْ بِاسْمِ رَبِّكَ الَّذِي خَلَقَ", "Oku! Seni yaratan Rabbinin adı ile oku", "Beginning"),
        DailyVerse(15, 112, "Ikhlas", 1, "قُلْ هُوَ اللَّهُ أَحَدٌ", "De ki: Allah birdir", "Oneness")
    )

    fun getVerseForDay(dayOfYear: Int): DailyVerse {
        val index = (dayOfYear - 1) % verses.size
        return verses[index]
    }
}
