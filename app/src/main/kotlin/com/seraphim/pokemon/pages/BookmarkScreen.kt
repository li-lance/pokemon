package com.seraphim.pokemon.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.seraphim.core.ui.topbar.SeraphimTopBar
import com.seraphim.pokemon.ui.theme.PokemonTheme

@Destination<RootGraph>
@Composable
fun BookmarkScreen() {
        Column(modifier = Modifier.fillMaxSize()){
            SeraphimTopBar("Pokemon")
            Button(onClick = {}) {
                Text(text = "Click Bookmark")
            }
        }
}

@Preview(showBackground = true)
@Composable
fun BookmarkScreenPreview() {
    PokemonTheme {
        BookmarkScreen()
    }
}