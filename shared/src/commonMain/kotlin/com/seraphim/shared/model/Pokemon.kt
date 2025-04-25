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
)