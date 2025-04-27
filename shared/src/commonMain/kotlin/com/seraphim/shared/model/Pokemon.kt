package com.seraphim.shared.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
class Pokemon(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
) {
    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                "pokemon/other/official-artwork/$index.png"
    }

//    fun name(): String = name.replaceFirstChar { it.uppercase() }
}