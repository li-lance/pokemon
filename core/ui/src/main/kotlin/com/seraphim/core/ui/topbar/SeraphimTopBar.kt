package com.seraphim.core.ui.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SeraphimTopBar(
    title: String,
    modifier: Modifier = Modifier,
    isBack:Boolean = true,
    onBackClick: (() -> Unit)? = null,
    startButton: @Composable (() -> Unit)? = null,
    endButton: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
        title = {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
        },
        navigationIcon = {
            if (isBack && onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back"
                    )
                }
            } else if (startButton != null) {
                Box(
                    modifier = Modifier
                ) {
                    startButton()
                }
            }
        },
        actions = {
            if (endButton != null) {
                Box(
                    modifier = Modifier
                ) {
                    endButton()
                }
            }
        }
    )
}