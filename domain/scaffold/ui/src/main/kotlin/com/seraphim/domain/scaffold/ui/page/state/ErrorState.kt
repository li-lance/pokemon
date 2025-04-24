package com.seraphim.domain.scaffold.ui.page.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorState(error: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Error Icon",
            modifier = Modifier.size(64.dp),
            tint = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(error, style = MaterialTheme.typography.bodyMedium, color = Color.Red)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("重试")
        }
    }
}

@Preview
@Composable
fun ErrorStatePreview() {
    ErrorState(
        error = "网络异常，请检查您的网络连接",
        onRetry = {}
    )
}