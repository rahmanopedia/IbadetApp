package com.ibadetapp.data.model

/**
 * Represents a Quran audio recitation source
 */
data class AudioRecitation(
    val id: String,
    val name: String, // Reciter name
    val language: String = "ar", // ar: Arabic
    val category: String = "full" // full: complete sura, ayah: individual ayah
)

/**
 * Collection of popular Quran recitation sources
 */
object RecitationList {
    val reciters = listOf(
        AudioRecitation("abdulbasit", "Abdulbasit Abdulsamad", "ar"),
        AudioRecitation("alafasy", "Mishari Al-Afasy", "ar"),
        AudioRecitation("alminshawi", "Muhammad Al-Minshawi", "ar"),
        AudioRecitation("paran", "Yusuf Paran", "ar"),
        AudioRecitation("maher", "Maher Al-Mueaqly", "ar")
    )

    fun getReciterById(id: String): AudioRecitation? =
        reciters.find { it.id == id }

    fun getDefaultReciter(): AudioRecitation = reciters[0]

    /**
     * Get audio URL for a specific sura
     * Using Everyayah.com API as source
     */
    fun getSurahUrl(reciterId: String, surahNumber: Int): String {
        val paddedSurah = surahNumber.toString().padStart(3, '0')
        return "https://everyayah.com/data/$reciterId/$paddedSurah.mp3"
    }

    /**
     * Get audio URL for a specific ayah
     */
    fun getAyahUrl(reciterId: String, surahNumber: Int, ayahNumber: Int): String {
        val paddedSurah = surahNumber.toString().padStart(3, '0')
        val paddedAyah = ayahNumber.toString().padStart(3, '0')
        return "https://everyayah.com/data/$reciterId/${paddedSurah}${paddedAyah}.mp3"
    }
}
