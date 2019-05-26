package com.example.androidlab4.presenter

import com.example.androidlab4.model.Pokemon.Type
import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.view.SearchView

class SearchPresenter(private val view: SearchView) {

    private val pokemonModel = PokemonModel()

    fun searchPokemon(name: String) {
        pokemonModel.searchPokemon(this, name)
    }

    //pokemon
    fun onFinished(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?) {
        view.updatePokemonImage(frontUrl, backUrl)
        view.updatePokemonData(name, types, weight)
    }
}