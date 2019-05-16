package com.example.androidlab4.view

import com.example.androidlab4.model.Pokemon.Type

interface SearchView {
    fun updatePokemonImage(frontUrl: String?, backUrl: String?)
    fun updatePokemonData(name: String?, types: List<Type>?, weight: Int?)
}