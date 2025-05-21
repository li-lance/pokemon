package com.seraphim.shared.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.seraphim.pokemon.model.PokemonDetailTypesInner
import kotlinx.serialization.json.Json


@ProvidedTypeConverter
class TypeResponseConverter {
    @TypeConverter
    fun fromString(value: String): List<PokemonDetailTypesInner>? {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<PokemonDetailTypesInner>?): String {
        return Json.encodeToString(type)
    }
}