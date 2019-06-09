package com.example.androidlab4.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [PokemonEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}