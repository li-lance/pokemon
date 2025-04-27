package com.seraphim.pokemon.di

import com.seraphim.pokemon.PokemonModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pokemonModule = module {
    viewModelOf(::PokemonModel)
}