package com.ibadetapp.util

/**
 * Input validation helper for user inputs
 * Validates zikir creation, bookmarks, and other user-provided data
 */
object ValidationHelper {

    /**
     * Validation result sealed class
     */
    sealed class ValidationResult {
        object Valid : ValidationResult()
        data class Invalid(val errorMessage: String) : ValidationResult()
    }

    /**
     * Validates Arabic text input
     * @param text Arabic text to validate
     * @return ValidationResult
     */
    fun validateArabicText(text: String): ValidationResult {
        return when {
            text.isBlank() -> ValidationResult.Invalid("Arapça metin boş olamaz")
            text.length < 2 -> ValidationResult.Invalid("Arapça metin en az 2 karakter olmalı")
            text.length > 200 -> ValidationResult.Invalid("Arapça metin 200 karakteri aşamaz")
            else -> ValidationResult.Valid
        }
    }

    /**
     * Validates Turkish text input
     * @param text Turkish text to validate
     * @return ValidationResult
     */
    fun validateTurkishText(text: String): ValidationResult {
        return when {
            text.isBlank() -> ValidationResult.Invalid("Türkçe metin boş olamaz")
            text.length < 2 -> ValidationResult.Invalid("Türkçe metin en az 2 karakter olmalı")
            text.length > 200 -> ValidationResult.Invalid("Türkçe metin 200 karakteri aşamaz")
            else -> ValidationResult.Valid
        }
    }

    /**
     * Validates transliteration input
     * @param text Transliteration text to validate
     * @return ValidationResult
     */
    fun validateTransliteration(text: String): ValidationResult {
        return when {
            text.isBlank() -> ValidationResult.Invalid("Okunuş boş olamaz")
            text.length < 2 -> ValidationResult.Invalid("Okunuş en az 2 karakter olmalı")
            text.length > 100 -> ValidationResult.Invalid("Okunuş 100 karakteri aşamaz")
            else -> ValidationResult.Valid
        }
    }

    /**
     * Validates target count input
     * @param count Target count to validate
     * @param maxAllowed Maximum allowed count
     * @return ValidationResult
     */
    fun validateTargetCount(count: Int, maxAllowed: Int = 10000): ValidationResult {
        return when {
            count <= 0 -> ValidationResult.Invalid("Hedef sayı 1'den büyük olmalı")
            count > maxAllowed -> ValidationResult.Invalid("Hedef sayı $maxAllowed'den fazla olamaz")
            else -> ValidationResult.Valid
        }
    }

    /**
     * Validates category input
     * @param category Category to validate
     * @return ValidationResult
     */
    fun validateCategory(category: String): ValidationResult {
        return when {
            category.isBlank() -> ValidationResult.Invalid("Kategori boş olamaz")
            category.length > 50 -> ValidationResult.Invalid("Kategori 50 karakteri aşamaz")
            else -> ValidationResult.Valid
        }
    }

    /**
     * Comprehensive validation for complete zikir input
     * @param arabicText Arabic text
     * @param turkishText Turkish text
     * @param transliteration Transliteration
     * @param targetCount Target count
     * @param category Category
     * @return ValidationResult
     */
    fun validateZikirInput(
        arabicText: String,
        turkishText: String,
        transliteration: String,
        targetCount: Int,
        category: String
    ): ValidationResult {
        // Validate each field
        validateArabicText(arabicText).let {
            if (it is ValidationResult.Invalid) return it
        }

        validateTurkishText(turkishText).let {
            if (it is ValidationResult.Invalid) return it
        }

        validateTransliteration(transliteration).let {
            if (it is ValidationResult.Invalid) return it
        }

        validateTargetCount(targetCount).let {
            if (it is ValidationResult.Invalid) return it
        }

        validateCategory(category).let {
            if (it is ValidationResult.Invalid) return it
        }

        return ValidationResult.Valid
    }

    /**
     * Gets error message from validation result
     * @param result ValidationResult
     * @return Error message or empty string if valid
     */
    fun getErrorMessage(result: ValidationResult): String {
        return when (result) {
            is ValidationResult.Valid -> ""
            is ValidationResult.Invalid -> result.errorMessage
        }
    }
}
