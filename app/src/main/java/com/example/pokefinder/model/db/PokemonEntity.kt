package com.example.pokefinder.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokefinder.model.pokemon.Type
import com.example.pokefinder.utils.Constants

@Entity(tableName = Constants.POKEMON_TABLE)
data class PokemonEntity(

    @PrimaryKey val name: String,
    @ColumnInfo val types: List<Type>?,
    @ColumnInfo val weight: Int?,
    @ColumnInfo(name = Constants.FRONT_URL) val frontUrl: String?,
    @ColumnInfo(name = Constants.BACK_URL) val backUrl: String?

)