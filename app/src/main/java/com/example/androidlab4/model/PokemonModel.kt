package com.example.androidlab4.model

import android.util.Log
import com.example.androidlab4.di.DaggerModelInjector
import com.example.androidlab4.di.ModelInjector
import com.example.androidlab4.di.NetworkModule
import com.example.androidlab4.model.Pokemon.Pokemon
import com.example.androidlab4.presenter.SearchPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


open class PokemonModel {

    private var injector: ModelInjector = DaggerModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    @Inject
    lateinit var api: ApiServiceInterface

    fun setup() {
        injector.inject(this)
    }

    open fun searchPokemon(onFinishedListener: SearchPresenter, searchName: String?) {
        if(!searchName.isNullOrBlank()) {
            val call: Call<Pokemon>? = api.getPokemon(searchName)
            call?.enqueue(object: Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(response.code() == 200) {
                        val pokemon = response.body()
                        getData(onFinishedListener, pokemon)
                    }
                    else {
                        Log.d("PokemonModel", "Failed to get pokemon")
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.d("PokemonModel", t.message)
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
}