package com.seraphim.pokemon.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.seraphim.pokemon.PokemonViewModel
import com.seraphim.pokemon.ui.theme.PokemonShape
import com.seraphim.shared.model.Pokemon
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon
) {
    val viewModel: PokemonViewModel = koinViewModel()
    var headerBackgroundColor by remember { mutableStateOf(Color.White) }
    LaunchedEffect(Unit) {
        viewModel.getPokemonDetail(pokemon.name)
    }
    Column {
        Box(
            modifier = Modifier
                .background(headerBackgroundColor, PokemonShape.medium)
                .statusBarsPadding()
                .aspectRatio(3f / 2f)
        ) {
            TransformImage(pokemon.getImageUrl(), pokemon.name) {
                headerBackgroundColor = it
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = pokemon.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}