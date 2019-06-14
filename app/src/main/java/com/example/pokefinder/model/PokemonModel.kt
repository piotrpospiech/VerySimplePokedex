package com.example.pokefinder.model

import android.util.Log
import com.example.pokefinder.di.component.DaggerPokemonModelComponent
import com.example.pokefinder.di.component.PokemonModelComponent
import com.example.pokefinder.di.module.ApiModule
import com.example.pokefinder.di.module.RepositoryModule
import com.example.pokefinder.model.Pokemon.Pokemon
import com.example.pokefinder.model.db.PokemonEntity
import com.example.pokefinder.model.db.PokemonRepository
import com.example.pokefinder.presenter.SearchPresenter
import com.example.pokefinder.utils.Constants
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PokemonModel {

    private var component: PokemonModelComponent = DaggerPokemonModelComponent
        .builder()
        .apiModule(ApiModule)
        .repositoryModule(RepositoryModule)
        .build()

    @Inject
    lateinit var api: ApiServiceInterface
    @Inject
    lateinit var pokemonRepository: PokemonRepository

    fun setup() {
        component.inject(this)
        pokemonRepository.setup()
        pokemonRepository.deleteAll()
    }

    fun searchPokemon(onFinishedListener: SearchPresenter, searchName: String?) {
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
                        onFinishedListener.sendMessage(Constants.CANNOT_FIND_POKEMON)
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
        onFinishedListener.onFinished(
            pokemon?.sprites?.front_default,
            pokemon?.sprites?.back_default,
            pokemon?.name,
            pokemon?.types,
            pokemon?.weight
        )
    }

    private fun storageInDB(pokemon: Pokemon?) {
        if(pokemon != null) {
            val pokemonResult = PokemonEntity(
                pokemon.name,
                pokemon.types,
                pokemon.weight,
                pokemon.sprites.front_default,
                pokemon.sprites.back_default
            )
            doAsync { pokemonRepository.savePokemon(pokemonResult) }
        }
    }
}