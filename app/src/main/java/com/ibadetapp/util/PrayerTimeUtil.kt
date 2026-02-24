package com.ibadetapp.util

import java.util.*

/**
 * Utility for converting Gregorian dates to Hijri (Islamic) calendar
 * Uses mathematical conversion formulas for accuracy
 */
object PrayerTimeUtil {

    /**
     * Converts the current Gregorian date to Hijri date format
     * @return Formatted string: "day month_name year H." (e.g., "15 Ramazan 1445 H.")
     */
    fun getHijriDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Basit Hicri takvim dönüşümü (yaklaşık)
        val jd = gregorianToJulian(year, month, day)
        val (hYear, hMonth, hDay) = julianToHijri(jd)

        val hijriMonths = arrayOf(
            "Muharrem", "Safer", "Rebiülevvel", "Rebiülahir",
            "Cemaziyelevvel", "Cemaziyelahir", "Recep", "Şaban",
            "Ramazan", "Şevval", "Zilkade", "Zilhicce"
        )

        val monthName = if (hMonth in 1..12) hijriMonths[hMonth - 1] else "Bilinmiyor"
        return "$hDay $monthName $hYear H."
    }

    private fun gregorianToJulian(year: Int, month: Int, day: Int): Long {
        val a = (14 - month) / 12
        val y = year + 4800 - a
        val m = month + 12 * a - 3
        return day + (153 * m + 2) / 5 + 365L * y + y / 4 - y / 100 + y / 400 - 32045L
    }

    private fun julianToHijri(jd: Long): Triple<Int, Int, Int> {
        val z = jd - 1948439 + 10632
        val n = (z - 1) / 10631
        val z2 = z - 10631 * n + 354
        val j = ((180 - 11 * z2) / 5420).toInt()
        val z3 = z2 - (11L * j + 14) / 30
        val year = (30 * n + j - 30).toInt()
        val month = ((z3 - 1) / 29.5 + 1).toInt().coerceIn(1, 12)
        val day = (z3 - (29.5 * (month - 1)).toLong()).toInt()
        return Triple(year, month, day)
    }
}
