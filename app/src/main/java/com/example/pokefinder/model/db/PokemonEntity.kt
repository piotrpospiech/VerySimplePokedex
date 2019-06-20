package com.example.pokefinder.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokefinder.model.pokemon.Type

@Entity(tableName = "pokemon_table")
data class PokemonEntity(

    @PrimaryKey val name: String,
    @ColumnInfo val types: List<Type>?,
    @ColumnInfo val weight: Int?,
    @ColumnInfo(name = "front_url") val frontUrl: String?,
    @ColumnInfo(name = "back_url") val backUrl: String?

)