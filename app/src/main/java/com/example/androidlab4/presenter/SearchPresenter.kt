package com.example.androidlab4.presenter

import com.example.androidlab4.model.Pokemon.Type
import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.utils.Constants
import com.example.androidlab4.view.SearchView
import javax.inject.Inject

class SearchPresenter @Inject constructor() {

    private val pokemonModel = PokemonModel()
    private lateinit var view: SearchView

    fun attach(view: SearchView) {
        this.view = view
        pokemonModel.setup()
    }

    fun searchPokemon(name: String) {
        pokemonModel.searchPokemon(this, name)
    }

    fun onFinished(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?) {
        val pokemonData: ArrayList<ArrayList<String>> = ArrayList()

        // name
        val nameList = ArrayList<String>()
        nameList.add(Constants.NAME)
        nameList.add(name?.capitalize().toString())
        pokemonData.add(nameList)

        // types
        val typesResult = StringBuilder()

        if(types != null) {
            for(i in 0 until types.size) {
                typesResult.append(types[i].type.name.capitalize())
                if(i < types.size-1) typesResult.append(", ")
            }
        }

        val typesList = ArrayList<String>()
        typesList.add(Constants.TYPES)
        typesList.add(typesResult.toString())
        pokemonData.add(typesList)

        // weight
        val weightList = ArrayList<String>()
        weightList.add(Constants.WEIGHT)
        weightList.add("${weight?.div(10.0)} kg")
        pokemonData.add(weightList)

        view.updatePokemon(frontUrl, backUrl, pokemonData)
    }
}