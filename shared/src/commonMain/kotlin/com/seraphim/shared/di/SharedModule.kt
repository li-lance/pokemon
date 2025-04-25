package com.seraphim.shared.di

import com.seraphim.pokemon.api.BerriesApi
import com.seraphim.pokemon.api.ContestsApi
import com.seraphim.pokemon.api.EncountersApi
import com.seraphim.pokemon.api.EvolutionApi
import com.seraphim.pokemon.api.GamesApi
import com.seraphim.pokemon.api.ItemsApi
import com.seraphim.pokemon.api.LocationApi
import com.seraphim.pokemon.api.MachinesApi
import com.seraphim.pokemon.api.MovesApi
import com.seraphim.pokemon.api.PokemonApi
import com.seraphim.pokemon.api.UtilityApi
import com.seraphim.pokemon.invoker.infrastructure.ApiClient
import com.seraphim.shared.database.AppDatabase
import com.seraphim.shared.database.AppDatabaseConstructor
import io.ktor.client.HttpClient
import org.koin.dsl.module

val sharedCommonModule = module {

    single { PokemonApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { BerriesApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { ContestsApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { EncountersApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { EvolutionApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { GamesApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { ItemsApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { LocationApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { MachinesApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { MovesApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }
    single { UtilityApi(ApiClient.BASE_URL, httpClient = get<HttpClient>()) }

    single { AppDatabaseConstructor.initialize() }

    single { get<AppDatabase>().pokemonDao() }
}