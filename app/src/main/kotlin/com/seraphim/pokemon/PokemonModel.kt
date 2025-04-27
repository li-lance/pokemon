package com.seraphim.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seraphim.shared.model.Pokemon
import com.seraphim.shared.model.Resource
import com.seraphim.shared.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PokemonModel(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemonList = MutableStateFlow(mutableListOf<Pokemon>())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        getList()
    }

    private fun getList() {
        repository.pokemonList(10).catch {
            it.printStackTrace()
        }.onEach {
            when (it) {
                is Resource.Success -> {
                    _pokemonList.value = it.data.toMutableList()
                }

                is Resource.Failed -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}