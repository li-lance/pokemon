package com.seraphim.shared.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.seraphim.shared.model.Pokemon
import com.seraphim.shared.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert
    suspend fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM Pokemon")
    fun getAllPokemons(): Flow<List<Pokemon>>

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)

    @Query("SELECT * FROM Pokemon LIMIT :limit OFFSET :offset")
    fun getPokemonsByPage(offset: Int, limit: Int): Flow<List<Pokemon>>

    @Insert
    fun insertPokemonInfo(toPokemonInfo: PokemonInfo)

    @Query("SELECT * FROM PokemonInfo WHERE name = :name")
    fun getPokemonInfoByName(name: String): Flow<PokemonInfo?>
}