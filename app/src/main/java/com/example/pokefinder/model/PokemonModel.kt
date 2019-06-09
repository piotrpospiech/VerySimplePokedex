package com.example.pokefinder.model

import android.util.Log
import com.example.pokefinder.di.component.DaggerPokemonModelComponent
import com.example.pokefinder.di.component.PokemonModelComponent
import com.example.pokefinder.di.module.ApiModule
import com.example.pokefinder.model.Pokemon.Pokemon
import com.example.pokefinder.model.db.PokemonEntity
import com.example.pokefinder.model.db.PokemonRepository
import com.example.pokefinder.presenter.SearchPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

open class PokemonModel {

    private lateinit var pokemonRepository: PokemonRepository

    private var component: PokemonModelComponent = DaggerPokemonModelComponent
        .builder()
        .apiModule(ApiModule)
        .build()

    @Inject
    lateinit var api: ApiServiceInterface

    fun setup() {
        component.inject(this)
        pokemonRepository = PokemonRepository()
        pokemonRepository.setup()
        pokemonRepository.deleteAll()
    }

    open fun searchPokemon(onFinishedListener: SearchPresenter, searchName: String?) {
        pokemonRepository.getAllPokemons()
        if(!searchName.isNullOrBlank()) {
            val call: Call<Pokemon>? = api.getPokemon(searchName)
            call?.enqueue(object: Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(response.code() == 200) {
                        val pokemon = response.body()
                        storageInDB(pokemon)
                        getData(onFinishedListener, pokemon)
                    }
                    else {
                        Log.d("PokemonModel", "Failed to get pokemon")
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.d("PokemonModel", t.message)
                    for (i in pokemonRepository.allPokemons) {
                        if (i.name == searchName) {
                            onFinishedListener.onFinished(i.frontUrl, i.backUrl, i.name, i.types, i.weight)
                        }
                    }
                }
            })
        }
    }

    private fun getData(onFinishedListener: SearchPresenter, pokemon: Pokemon?) {
        val name = pokemon?.name

        val types = pokemon?.types
        val typesResult = StringBuilder()
        types?.forEach {
            typesResult.append(it.type.name)
            typesResult.append(", ")
        }

        val weight = pokemon?.weight
        val frontUrl = pokemon?.sprites?.front_default
        val backUrl = pokemon?.sprites?.back_default

        onFinishedListener.onFinished(frontUrl, backUrl, name, types, weight)
    }

    private fun storageInDB(pokemon: Pokemon?) {
        if(pokemon != null) {
            val name = pokemon.name
            val types = pokemon.types
            val weight = pokemon.weight
            val frontUrl = pokemon.sprites.front_default
            val backUrl = pokemon.sprites.back_default

            val pokemonResult = PokemonEntity(name, types, weight, frontUrl, backUrl)
            pokemonRepository.savePokemon(pokemonResult)
        }
    }
}