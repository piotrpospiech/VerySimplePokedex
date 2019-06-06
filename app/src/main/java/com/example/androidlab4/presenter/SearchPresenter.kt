package com.example.androidlab4.presenter

import com.example.androidlab4.model.Pokemon.Type
import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.utils.Constants
import com.example.androidlab4.view.SearchView
import javax.inject.Inject

open class SearchPresenter @Inject constructor() {

    private lateinit var pokemonModel: PokemonModel
    private lateinit var view: SearchView

    fun attach(view: SearchView, pokemonModel: PokemonModel) {
        this.view = view
        this.pokemonModel = pokemonModel
    }

    fun searchPokemon(name: String?) {
        if(!name.isNullOrBlank()) {
            if(name.length == Constants.SECRET_STRING_LENGHT) {
                view.showSecretToast()
            } else {
                pokemonModel.searchPokemon(this, name)
            }
        }
    }

    open fun onFinished(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?) {
        val pokemonData: ArrayList<ArrayList<String>> = ArrayList()

        pokemonData.add(getName(name))
        pokemonData.add(getTypes(types))
        pokemonData.add(getTypes(weight))

        view.updatePokemon(frontUrl, backUrl, pokemonData)
    }

    private fun getName(name: String?): ArrayList<String> {
        val nameList = ArrayList<String>()
        nameList.add(Constants.NAME)
        nameList.add(name?.capitalize().toString())
        return nameList
    }

    private fun getTypes(types: List<Type>?): ArrayList<String> {
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
        return typesList
    }

    private fun getTypes(weight: Int?): ArrayList<String> {
        val weightList = ArrayList<String>()
        weightList.add(Constants.WEIGHT)
        weightList.add("${weight?.div(10.0)} kg")
        return weightList
    }
}