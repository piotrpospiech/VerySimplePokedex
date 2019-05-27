package com.example.androidlab4.view

import com.example.androidlab4.model.Pokemon.Type

interface SearchView {
    fun updatePokemon(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?)
}