package com.seraphim.pokemon.pages

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.PokemonDetailScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.seraphim.core.ui.topbar.SeraphimTopBar
import com.seraphim.pokemon.PokemonViewModel
import com.seraphim.pokemon.ui.theme.PokemonTheme
import com.seraphim.shared.model.Pokemon
import org.koin.androidx.compose.koinViewModel

@Destination<RootGraph>(start = true)
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    Column(modifier = Modifier.fillMaxSize()) {
        SeraphimTopBar("Pokemon")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.small)
                .padding(12.dp)
                .clickable {
                    //onSearchClick()
                },
        ) {
            Text(
                text = "搜索宝可梦",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.weight(1f)
            )
        }
        PokemonFeature(navigator)
    }
}


@Composable
fun ClearableTextField() {
    val textState = remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier.weight(1f),
            label = { Text("输入内容") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent, focusedBorderColor = Color.Transparent
            ),
            trailingIcon = {
                if (textState.value.isNotEmpty()) {
                    IconButton(onClick = { textState.value = "" }) {
                        Icon(
                            imageVector = Icons.Default.Close, contentDescription = "清除输入"
                        )
                    }
                }
            })
    }
}

@Composable
fun PokemonFeature(
    navigator: DestinationsNavigator,
    viewModel: PokemonViewModel = koinViewModel()
) {
    val pokemonItems: LazyPagingItems<Pokemon> =
        viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
    Text(
        "Pokemon Feature",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.labelMedium
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier.padding(16.dp)
    ) {
        items(pokemonItems.itemCount) { index ->
            val pokemon = pokemonItems[index]
            pokemon?.let {
                PokemonCard(pokemon, navigator)
            }
        }
    }
}


@Composable
fun PokemonCard(pokemon: Pokemon, navigator: DestinationsNavigator) {
    var backgroundColor by remember { mutableStateOf(Color.White) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navigator.navigate(PokemonDetailScreenDestination(pokemon.getImageUrl()))
            },
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.getImageUrl())
                    .diskCachePolicy(CachePolicy.ENABLED) // 启用磁盘缓存
                    .networkCachePolicy(CachePolicy.ENABLED) // 启用网络缓存
                    .build(),
                transform = { state ->
                    if (state is AsyncImagePainter.State.Success) {
                        val drawable = state.result.drawable
                        val bitmap = drawable.toBitmap().copy(Bitmap.Config.ARGB_8888, true)
                        val palette = Palette.from(bitmap).generate()
                        backgroundColor = Color(palette.getDominantColor(Color.White.toArgb()))
                    }
                    state
                },
                contentDescription = pokemon.name,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = pokemon.name,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PokemonTheme {
//        HomeScreen()
    }
}