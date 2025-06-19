package com.seraphim.shared.repository

import com.seraphim.pokemon.api.PokemonApi
import com.seraphim.pokemon.invoker.infrastructure.HttpResponse
import com.seraphim.pokemon.model.PokemonDetail
import com.seraphim.shared.PokemonConstant.MAX_EXP
import com.seraphim.shared.database.PokemonDao
import com.seraphim.shared.model.PokemonInfo
import com.seraphim.shared.model.Resource
import com.seraphim.shared.network.NetworkBoundRepository
import com.seraphim.utils.defaultIfNull
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class PokemonRepository(private val api: PokemonApi, private val dao: PokemonDao) {

    fun getPokemonDetail(name: String): Flow<Resource<PokemonInfo?>> {
        return object : NetworkBoundRepository<PokemonInfo?, PokemonDetail>() {
            override suspend fun saveRemoteData(response: PokemonDetail) {
                dao.insertPokemonInfo(response.toPokemonInfo())
            }

            override fun fetchFromLocal(): Flow<PokemonInfo?> {
                return dao.getPokemonInfoByName(name)
            }

            override suspend fun fetchFromRemote(): HttpResponse<PokemonDetail> {
                return api.apiV2PokemonRetrieve(name)
            }
        }.asFlow()
    }
}

fun PokemonDetail.toPokemonInfo(): PokemonInfo {
    return PokemonInfo(
        id = id,
        name = name,
        height = height.defaultIfNull(),
        weight = weight.defaultIfNull(),
        experience = baseExperience.defaultIfNull(),
        types = types,
        exp = Random.nextInt(MAX_EXP),
        stats = stats,
    )
}