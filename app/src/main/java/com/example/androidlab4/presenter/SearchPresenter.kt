package com.example.androidlab4.presenter

import com.example.androidlab4.model.Pokemon.Type
import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.view.SearchView

class SearchPresenter {

    private val pokemonModel = PokemonModel()
    private lateinit var view: SearchView

    fun attach(view: SearchView) {
        this.view = view
    }

    fun searchPokemon(name: String) {
        pokemonModel.searchPokemon(this, name)
    }

    fun onFinished(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?) {
        view.updatePokemon(frontUrl, backUrl, name, types, weight)
    }
}