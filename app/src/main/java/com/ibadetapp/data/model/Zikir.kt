package com.ibadetapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zikir_table")
data class Zikir(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val arabicText: String,
    val turkishText: String,
    val transliteration: String,
    val targetCount: Int = 33,
    val currentCount: Int = 0,
    val isCustom: Boolean = false,
    val category: String = "Genel"
)

// Önceden tanımlı zikirler
object DefaultZikirler {
    val list = listOf(
        Zikir(
            arabicText = "سُبْحَانَ اللَّهِ",
            turkishText = "Allah'ı tespih ederim",
            transliteration = "Sübhanallah",
            targetCount = 33,
            category = "Tesbih"
        ),
        Zikir(
            arabicText = "الْحَمْدُ لِلَّهِ",
            turkishText = "Hamd Allah'a mahsustur",
            transliteration = "Elhamdülillah",
            targetCount = 33,
            category = "Tesbih"
        ),
        Zikir(
            arabicText = "اللَّهُ أَكْبَرُ",
            turkishText = "Allah en büyüktür",
            transliteration = "Allahu Ekber",
            targetCount = 34,
            category = "Tesbih"
        ),
        Zikir(
            arabicText = "لَا إِلَهَ إِلَّا اللَّهُ",
            turkishText = "Allah'tan başka ilah yoktur",
            transliteration = "Lailaheillallah",
            targetCount = 100,
            category = "Kelime-i Tevhid"
        ),
        Zikir(
            arabicText = "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ",
            turkishText = "Allah'ım, Muhammed'e salat eyle",
            transliteration = "Allahümme salli ala Muhammed",
            targetCount = 100,
            category = "Salavat"
        ),
        Zikir(
            arabicText = "أَسْتَغْفِرُ اللَّهَ",
            turkishText = "Allah'tan bağışlanma dilerim",
            transliteration = "Estağfirullah",
            targetCount = 100,
            category = "İstiğfar"
        ),
        Zikir(
            arabicText = "لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            turkishText = "Güç ve kuvvet yalnız Allah'tandır",
            transliteration = "La havle vela kuvvete illa billah",
            targetCount = 33,
            category = "Havkale"
        ),
        Zikir(
            arabicText = "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ",
            turkishText = "Rahman ve Rahim olan Allah'ın adıyla",
            transliteration = "Bismillahirrahmanirrahim",
            targetCount = 21,
            category = "Besmele"
        )
    )
}
