package com.seraphim.pokemon

import com.seraphim.shared.model.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailRoute(val pokemon: Pokemon)