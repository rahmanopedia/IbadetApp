package com.ibadetapp.ui.state

import org.junit.Assert.*
import org.junit.Test

class UiStateTest {

    @Test
    fun `Loading state should return true for isLoading`() {
        val state = UiState.Loading
        assertTrue(state.isLoading)
        assertFalse(state.isSuccess)
        assertFalse(state.isError)
        assertFalse(state.isEmpty)
    }

    @Test
    fun `Success state should return true for isSuccess`() {
        val data = "test"
        val state = UiState.Success(data)
        assertFalse(state.isLoading)
        assertTrue(state.isSuccess)
        assertFalse(state.isError)
        assertFalse(state.isEmpty)
    }

    @Test
    fun `Error state should return true for isError`() {
        val state = UiState.Error("Test error")
        assertFalse(state.isLoading)
        assertFalse(state.isSuccess)
        assertTrue(state.isError)
        assertFalse(state.isEmpty)
    }

    @Test
    fun `Empty state should return true for isEmpty`() {
        val state = UiState.Empty("No data")
        assertFalse(state.isLoading)
        assertFalse(state.isSuccess)
        assertFalse(state.isError)
        assertTrue(state.isEmpty)
    }

    @Test
    fun `Error state should preserve message`() {
        val message = "Custom error message"
        val state = UiState.Error(message)
        assertEquals(message, (state as UiState.Error).message)
    }

    @Test
    fun `map should transform Success data`() {
        val state = UiState.Success(5)
        val mapped = state.map { it * 2 }
        assertTrue(mapped.isSuccess)
        assertEquals(10, (mapped as UiState.Success).data)
    }

    @Test
    fun `map should preserve Error state`() {
        val error = UiState.Error("Test error")
        val mapped = error.map { "unused" }
        assertTrue(mapped.isError)
        assertEquals("Test error", (mapped as UiState.Error).message)
    }
}
