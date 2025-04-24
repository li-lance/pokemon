package com.seraphim.domain.scaffold.ui.page.state

import androidx.compose.runtime.Composable
import com.seraphim.core.ui.state.UiState

@Composable
fun PageState(uiState: UiState) {
    when (uiState) {
        is UiState.Loading -> {
            LoadingState()
        }

        is UiState.Error -> {
            ErrorState(error = uiState.error, onRetry = uiState.onRetry)
        }

        is UiState.Empty -> {
            EmptyState()
        }

        is UiState.Success -> {
            uiState.content()
        }
    }
}