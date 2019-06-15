package com.example.pokefinder.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PokemonDao {

    @Query("SELECT COUNT(name) FROM pokemon_table")
    fun getSize(): Int

    @Query("SELECT * FROM pokemon_table WHERE name LIKE :name")
    fun getByName(name: String): PokemonEntity

    @Insert
    fun insert(pokemon: PokemonEntity)

    @Query("DELETE FROM pokemon_table")
    fun deleteAll()
}