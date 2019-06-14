package com.example.pokefinder.model.db

import com.example.pokefinder.di.component.DaggerPokemonRepositoryComponent
import com.example.pokefinder.di.component.PokemonRepositoryComponent
import com.example.pokefinder.di.module.DatabaseModule
import org.jetbrains.anko.doAsync
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
        doAsync { allPokemons = getAll() }
        pokemonDao = pokemonDatabase.pokemonDao()
    }

    private fun getAll(): List<PokemonEntity> {
        return pokemonDao.getAll()
    }

    fun savePokemon(pokemon: PokemonEntity) {
        if (allPokemons.size > 20)  {
            deleteAll()
            allPokemons = listOf()
        }
        pokemonDao.insert(pokemon)
        allPokemons = getAll()
    }

    fun deleteAll() {
        pokemonDao.deleteAll()
    }
}