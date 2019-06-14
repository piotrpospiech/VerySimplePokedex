package com.example.pokefinder.model.db

import com.example.pokefinder.di.component.DaggerPokemonRepositoryComponent
import com.example.pokefinder.di.component.PokemonRepositoryComponent
import com.example.pokefinder.di.module.DatabaseModule
import javax.inject.Inject

class PokemonRepository {

    lateinit var allPokemons: List<PokemonEntity>
    private var component: PokemonRepositoryComponent = DaggerPokemonRepositoryComponent
        .builder()
        .databaseModule(DatabaseModule)
        .build()

    @Inject
    lateinit var pokemonDatabase: PokemonDatabase
    private lateinit var pokemonDao: PokemonDao

    fun setup() {
        component.inject(this)
        allPokemons  = emptyList()
        pokemonDao = pokemonDatabase.pokemonDao()
    }

    fun getAll(): List<PokemonEntity> {
        return pokemonDao.getAll()
    }

    fun savePokemon(pokemon: PokemonEntity) {
        pokemonDao.insert(pokemon)
    }

    fun deleteAll() {
        pokemonDao.delete()
    }
}