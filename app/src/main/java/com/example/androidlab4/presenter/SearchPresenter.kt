package com.example.androidlab4.presenter

import com.example.androidlab4.model.Pokemon.Pokemon
import com.example.androidlab4.model.Pokemon.Type
import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.view.SearchActivity

class SearchPresenter(private val view: SearchActivity) {

    var pokemon: Pokemon? = null
    private val pokemonModel = PokemonModel()

    fun searchPokemon(name: String) {
        pokemonModel.searchPokemon(this, name)
    }

    fun onFinished(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?) {
        view.updatePokemonImage(frontUrl, backUrl)
        view.updatePokemonData(name, types, weight)
    }
}