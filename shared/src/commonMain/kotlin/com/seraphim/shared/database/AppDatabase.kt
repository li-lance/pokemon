package com.seraphim.shared.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.seraphim.shared.model.Pokemon
import com.seraphim.shared.model.PokemonInfo

@Database(entities = [Pokemon::class, PokemonInfo::class], version = 3)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(value = [TypeResponseConverter::class, StatsResponseConverter::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
internal const val DB_FILE_NAME = "pokemon.db"