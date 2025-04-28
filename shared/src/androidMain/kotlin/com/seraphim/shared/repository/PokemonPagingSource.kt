package com.seraphim.shared.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seraphim.pokemon.api.PokemonApi
import com.seraphim.pokemon.invoker.infrastructure.HttpResponse
import com.seraphim.pokemon.model.PaginatedPokemonSummaryList
import com.seraphim.shared.database.PokemonDao
import com.seraphim.shared.model.Pokemon
import com.seraphim.shared.model.Resource
import com.seraphim.shared.network.NetworkBoundRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PokemonPagingSource(
    private val api: PokemonApi,
    private val dao: PokemonDao
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 0
        val limit = params.loadSize

        return try {
            // 使用 NetworkBoundRepository 逻辑
            val repository =
                object : NetworkBoundRepository<List<Pokemon>, PaginatedPokemonSummaryList>() {
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
                        return dao.getPokemonsByPage(page * limit, limit)
                    }

                    override suspend fun fetchFromRemote(): HttpResponse<PaginatedPokemonSummaryList> {
                        return api.pokemonList(limit = limit, offset = page * limit)
                    }
                }

            val data: List<Pokemon> =
                (repository.asFlow().first() as Resource.Success<List<Pokemon>>).data
            LoadResult.Page(
                data = data,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}