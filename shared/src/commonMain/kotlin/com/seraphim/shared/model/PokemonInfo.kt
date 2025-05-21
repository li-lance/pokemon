package com.seraphim.shared.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seraphim.pokemon.model.PokemonDetailTypesInner
import com.seraphim.pokemon.model.PokemonStat
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class PokemonInfo(
    @PrimaryKey val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<PokemonDetailTypesInner>,
    val exp: Int,
    val stats: List<PokemonStat>,
) {
}

