package com.seraphim.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.seraphim.shared.repository.PokemonPagingSource
import com.seraphim.shared.repository.PokemonRepository

class PokemonViewModel(
    private val repository: PokemonRepository,
    private val pagingSource: PokemonPagingSource
) : ViewModel() {
    val pokemonPagingFlow = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { pagingSource }
    ).flow.cachedIn(viewModelScope)
}