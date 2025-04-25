package com.seraphim.shared.repository

import android.util.Log
import com.seraphim.pokemon.api.PokemonApi
import com.seraphim.pokemon.invoker.infrastructure.HttpResponse
import com.seraphim.pokemon.model.PaginatedPokemonSummaryList
import com.seraphim.shared.database.PokemonDao
import com.seraphim.shared.model.Pokemon
import com.seraphim.shared.model.Resource
import com.seraphim.shared.network.NetworkBoundRepository
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val api: PokemonApi, private val dao: PokemonDao) {
    fun pokemonList(
        limit: Int? = null,
        offset: Int? = null,
        query: String? = null
    ): Flow<Resource<List<Pokemon>>> {
        return object : NetworkBoundRepository<List<Pokemon>, PaginatedPokemonSummaryList>() {
            override suspend fun saveRemoteData(response: PaginatedPokemonSummaryList) {
                response.results?.map {
                    val pokemon = Pokemon(
                        name = it.name,
                        url = it.url
                    )
                    dao.insertPokemon(pokemon)
                }
            }

            override fun fetchFromLocal(): Flow<List<Pokemon>> {
                return dao.getAllPokemons()
            }

            override suspend fun fetchFromRemote(): HttpResponse<PaginatedPokemonSummaryList> {
                return api.pokemonList(limit, offset, query)
            }
        }.asFlow()
    }

}