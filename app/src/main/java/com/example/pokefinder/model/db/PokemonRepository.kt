package com.example.pokefinder.model.db

import android.util.Log
import com.example.pokefinder.di.component.DaggerPokemonRepositoryComponent
import com.example.pokefinder.di.component.PokemonRepositoryComponent
import com.example.pokefinder.di.module.DatabaseModule
import org.jetbrains.anko.doAsyncResult
import javax.inject.Inject

class PokemonRepository {

    private var databaseSize: Int = 0
    private var component: PokemonRepositoryComponent = DaggerPokemonRepositoryComponent
        .builder()
        .databaseModule(DatabaseModule)
        .build()

    @Inject
    lateinit var pokemonDatabase: PokemonDatabase
    private lateinit var pokemonDao: PokemonDao

    fun setup() {
        component.inject(this)
        pokemonDao = pokemonDatabase.pokemonDao()
        databaseSize = doAsyncResult { getSize() }.get()
        Log.d("PokemonRepository", "DB size after setup: $databaseSize")
    }

    private fun getSize(): Int {
        return pokemonDao.getSize()
    }

    fun getByName(name: String): PokemonEntity {
        return pokemonDao.getByName(name)
    }

    fun savePokemon(pokemon: PokemonEntity) {
        if (databaseSize > 10)  {
            deleteAll()
            databaseSize = 0
        }
        pokemonDao.insert(pokemon)
        databaseSize++
    }

    private fun deleteAll() {
        pokemonDao.deleteAll()
    }
}