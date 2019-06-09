package com.example.pokefinder.view

interface SearchView {
    fun updatePokemon(frontUrl: String?, backUrl: String?, pokemonData: ArrayList<ArrayList<String>>?)
    fun showSecretToast()
}