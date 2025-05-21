package com.seraphim.shared.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.seraphim.pokemon.model.PokemonStat
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class StatsResponseConverter {

    @TypeConverter
    fun fromString(value: String): List<PokemonStat>? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<PokemonStat>?): String {
        return Json.encodeToString(type)
    }
}