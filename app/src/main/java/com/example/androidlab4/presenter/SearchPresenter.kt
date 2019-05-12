package com.example.androidlab4.presenter

import com.example.androidlab4.model.pokemon.Pokemon
import com.example.androidlab4.view.SearchView

class SearchPresenter(private val view: SearchView) {

    fun searchPokemon(name: String) {
        view.searchPokemon(name)
    }

    fun initializeTools() {
        view.initializeStetho()
        view.initializeRetrofit()
    }
}

interface SearchView {
    fun searchPokemon(name: String)
    fun updatePokemonImage(pokemon: Pokemon?)
    fun updatePokemonData(pokemon: Pokemon?)
    fun initializeStetho()
    fun initializeRetrofit()
}