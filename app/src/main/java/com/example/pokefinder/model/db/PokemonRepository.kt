package com.example.pokefinder.model.db

import android.os.AsyncTask
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
        PokemonRepository.InsertAsyncTask(pokemonDao, this).execute(pokemon)
    }

    fun deleteAll() {
        PokemonRepository.DeleteAsyncTask(pokemonDao).execute()
    }

    private class GetAllAsyncTask internal constructor(private val dao: PokemonDao, private val pokemonRepository: PokemonRepository) : AsyncTask<Void, Void, List<PokemonEntity>>() {
        override fun doInBackground(vararg params: Void?): List<PokemonEntity>? {
            return dao.getAll()
        }

        override fun onPostExecute(result: List<PokemonEntity>) {
            super.onPostExecute(result)
            pokemonRepository.allPokemons = result
        }

    }

    private class InsertAsyncTask internal constructor(private val dao: PokemonDao, private val pokemonRepository: PokemonRepository) : AsyncTask<PokemonEntity, Void,  Void>() {
        override fun doInBackground(vararg params: PokemonEntity): Void? {
            var isInDatabase = false
            for (i in pokemonRepository.allPokemons) {
                if (i.name == params[0].name) {
                    isInDatabase = true
                    break
                }
            }
            if (!isInDatabase) {
                dao.insert(params[0])
            }
            return null
        }
    }

    private class DeleteAsyncTask internal constructor(private val dao: PokemonDao) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            dao.delete()
            return null
        }
    }
}