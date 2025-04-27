package com.seraphim.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.seraphim.core.ui.state.edgeToEdgeWindowInsetsControllerCompat
import com.seraphim.pokemon.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        window.edgeToEdgeWindowInsetsControllerCompat()
        setContent {
            PokemonTheme {
                MainScreen()
            }
        }
    }
}

