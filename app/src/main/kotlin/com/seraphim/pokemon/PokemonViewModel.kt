package com.seraphim.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.seraphim.shared.model.Resource
import com.seraphim.shared.repository.PokemonRepository
import kotlinx.coroutines.flow.collectLatest

class PokemonViewModel(
    private val repository: PokemonRepository
) : ViewModel() {
    val pokemonPagingFlow = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { repository.createPokemonPagingSource() }
    ).flow.cachedIn(viewModelScope)

    suspend fun getPokemonDetail(name: String) {

        repository.getPokemonDetail(name).collectLatest {
            when (it) {
                is Resource.Success -> {
                    // Handle success
                }

                is Resource.Failed -> {
                    // Handle failure
                }
            }
        }
    }
}