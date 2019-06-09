package com.example.androidlab4.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    fun getAll(): List<PokemonEntity>

    @Insert
    fun insert(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemon_table")
    fun delete()
}