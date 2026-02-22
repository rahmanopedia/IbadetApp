package com.ibadetapp.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast

fun Context.copyToClipboard(text: String, label: String = "Kopyalandı") {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, text)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(this, "Panoya kopyalandı", Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Int.toArabicNumber(): String {
    val arabicDigits = charArrayOf('٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩')
    val sb = StringBuilder()
    var num = this
    if (num == 0) return "٠"
    while (num > 0) {
        sb.insert(0, arabicDigits[num % 10])
        num /= 10
    }
    return sb.toString()
}
