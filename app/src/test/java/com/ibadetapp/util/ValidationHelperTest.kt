package com.ibadetapp.util

import org.junit.Test
import org.junit.Assert.*

class ValidationHelperTest {

    @Test
    fun `validateArabicText should accept valid text`() {
        val result = ValidationHelper.validateArabicText("اللَّهُ")
        assertTrue(result is ValidationHelper.ValidationResult.Valid)
    }

    @Test
    fun `validateArabicText should reject empty text`() {
        val result = ValidationHelper.validateArabicText("")
        assertTrue(result is ValidationHelper.ValidationResult.Invalid)
        assertEquals("Arapça metin boş olamaz",
            (result as ValidationHelper.ValidationResult.Invalid).errorMessage)
    }

    @Test
    fun `validateArabicText should reject too short text`() {
        val result = ValidationHelper.validateArabicText("ا")
        assertTrue(result is ValidationHelper.ValidationResult.Invalid)
    }

    @Test
    fun `validateArabicText should reject too long text`() {
        val longText = "ا".repeat(201)
        val result = ValidationHelper.validateArabicText(longText)
        assertTrue(result is ValidationHelper.ValidationResult.Invalid)
    }

    @Test
    fun `validateTurkishText should accept valid text`() {
        val result = ValidationHelper.validateTurkishText("Subhanallah")
        assertTrue(result is ValidationHelper.ValidationResult.Valid)
    }

    @Test
    fun `validateTargetCount should accept valid count`() {
        val result = ValidationHelper.validateTargetCount(100)
        assertTrue(result is ValidationHelper.ValidationResult.Valid)
    }

    @Test
    fun `validateTargetCount should reject zero or negative`() {
        val result = ValidationHelper.validateTargetCount(0)
        assertTrue(result is ValidationHelper.ValidationResult.Invalid)
    }

    @Test
    fun `validateTargetCount should reject counts exceeding max`() {
        val result = ValidationHelper.validateTargetCount(10001)
        assertTrue(result is ValidationHelper.ValidationResult.Invalid)
    }

    @Test
    fun `validateCategory should accept valid category`() {
        val result = ValidationHelper.validateCategory("Tasbih")
        assertTrue(result is ValidationHelper.ValidationResult.Valid)
    }

    @Test
    fun `validateZikirInput should validate all fields`() {
        val result = ValidationHelper.validateZikirInput(
            arabicText = "سُبْحَانَ اللَّهِ",
            turkishText = "Subhanallah",
            transliteration = "Subhanallah",
            targetCount = 33,
            category = "Tasbih"
        )
        assertTrue(result is ValidationHelper.ValidationResult.Valid)
    }

    @Test
    fun `validateZikirInput should fail if any field is invalid`() {
        val result = ValidationHelper.validateZikirInput(
            arabicText = "",
            turkishText = "Subhanallah",
            transliteration = "Subhanallah",
            targetCount = 33,
            category = "Tasbih"
        )
        assertTrue(result is ValidationHelper.ValidationResult.Invalid)
    }

    @Test
    fun `getErrorMessage should return empty string for valid result`() {
        val result = ValidationHelper.ValidationResult.Valid
        assertEquals("", ValidationHelper.getErrorMessage(result))
    }

    @Test
    fun `getErrorMessage should return error message for invalid result`() {
        val result = ValidationHelper.ValidationResult.Invalid("Test error")
        assertEquals("Test error", ValidationHelper.getErrorMessage(result))
    }
}
