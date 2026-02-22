package com.ibadetapp.data.model

import com.google.gson.annotations.SerializedName

data class Surah(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
    @SerializedName("englishName") val englishName: String,
    @SerializedName("turkishName") val turkishName: String,
    @SerializedName("numberOfAyahs") val numberOfAyahs: Int,
    @SerializedName("revelationType") val revelationType: String,
    @SerializedName("ayahs") val ayahs: List<Ayah> = emptyList()
)

data class Ayah(
    @SerializedName("number") val number: Int,
    @SerializedName("numberInSurah") val numberInSurah: Int,
    @SerializedName("text") val arabicText: String,
    @SerializedName("turkishText") val turkishText: String = ""
)
