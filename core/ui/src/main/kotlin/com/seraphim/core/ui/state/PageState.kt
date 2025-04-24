package com.seraphim.core.ui.state

import androidx.compose.runtime.Composable

sealed class UiState {
    data object Loading : UiState()
    data class Empty(val title: String) : UiState()
    data class Error(val error: String, val onRetry: () -> Unit) : UiState()
    data class Success(val content: @Composable () -> Unit) : UiState()
}