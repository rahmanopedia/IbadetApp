package com.ibadetapp.util

import org.junit.Assert.*
import org.junit.Test

class ResultTest {

    @Test
    fun `Success result should return true for isSuccess`() {
        val result = Result.Success(42)
        assertTrue(result.isSuccess)
        assertFalse(result.isError)
        assertFalse(result.isLoading)
    }

    @Test
    fun `Error result should return true for isError`() {
        val exception = Exception("Test error")
        val result = Result.Error(exception)
        assertFalse(result.isSuccess)
        assertTrue(result.isError)
        assertFalse(result.isLoading)
    }

    @Test
    fun `Loading result should return true for isLoading`() {
        val result = Result.Loading
        assertFalse(result.isSuccess)
        assertFalse(result.isError)
        assertTrue(result.isLoading)
    }

    @Test
    fun `getOrNull should return data for Success`() {
        val data = "test data"
        val result = Result.Success(data)
        assertEquals(data, result.getOrNull())
    }

    @Test
    fun `getOrNull should return null for Error and Loading`() {
        val errorResult = Result.Error(Exception())
        val loadingResult = Result.Loading

        assertNull(errorResult.getOrNull())
        assertNull(loadingResult.getOrNull())
    }

    @Test
    fun `exceptionOrNull should return exception for Error`() {
        val exception = Exception("Test error")
        val result = Result.Error(exception)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `exceptionOrNull should return null for Success and Loading`() {
        val successResult = Result.Success(42)
        val loadingResult = Result.Loading

        assertNull(successResult.exceptionOrNull())
        assertNull(loadingResult.exceptionOrNull())
    }

    @Test
    fun `map should transform Success data`() {
        val result = Result.Success(5)
        val mappedResult = result.map { it * 2 }

        assertTrue(mappedResult.isSuccess)
        assertEquals(10, mappedResult.getOrNull())
    }

    @Test
    fun `map should preserve Error`() {
        val exception = Exception("Test error")
        val result = Result.Error(exception)
        val mappedResult = result.map { "unused" }

        assertTrue(mappedResult.isError)
        assertEquals(exception, mappedResult.exceptionOrNull())
    }

    @Test
    fun `map with exception should convert to Error`() {
        val result = Result.Success(5)
        val mappedResult = result.map { throw Exception("Transform error") }

        assertTrue(mappedResult.isError)
        assertNotNull(mappedResult.exceptionOrNull())
    }
}
