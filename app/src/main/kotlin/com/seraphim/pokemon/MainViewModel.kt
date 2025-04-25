package com.seraphim.pokemon

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seraphim.shared.model.Resource
import com.seraphim.shared.repository.PokemonRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val repository: PokemonRepository) : ViewModel() {
    fun getList() {
        repository.pokemonList(100).catch {
            it.printStackTrace()
        }.onEach {
            when (it) {
                is Resource.Success -> {
                }

                is Resource.Failed -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}