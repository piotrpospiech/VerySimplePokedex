package com.example.pokefinder.presenter

import com.example.pokefinder.di.component.DaggerSearchPresenterComponent
import com.example.pokefinder.di.component.SearchPresenterComponent
import com.example.pokefinder.di.module.PokemonModelModule
import com.example.pokefinder.model.Pokemon.Type
import com.example.pokefinder.model.PokemonModel
import com.example.pokefinder.utils.Constants
import com.example.pokefinder.view.SearchView
import javax.inject.Inject

class SearchPresenter {

    private var component: SearchPresenterComponent = DaggerSearchPresenterComponent
        .builder()
        .modelModule(PokemonModelModule)
        .build()

    @Inject
    lateinit var pokemonModel: PokemonModel
    private lateinit var view: SearchView

    fun setup(view: SearchView) {
        component.inject(this)
        this.view = view
        pokemonModel = PokemonModel()
        pokemonModel.setup()
    }

    fun searchPokemon(name: String?) {
        if (!name.isNullOrBlank()) pokemonModel.searchPokemon(this, name)
        else sendMessage(Constants.MESSAGE_WRONG_INPUT)
    }

    fun sendMessage(message: String) {
        view.showToast(message)
    }

    fun onFinished(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?) {
        val pokemonData: ArrayList<ArrayList<String>> = ArrayList()

        pokemonData.add(getName(name))
        pokemonData.add(getTypes(types))
        pokemonData.add(getWeight(weight))

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

    private fun getWeight(weight: Int?): ArrayList<String> {
        val weightList = ArrayList<String>()
        weightList.add(Constants.WEIGHT)
        weightList.add("${weight?.div(10.0)} kg")
        return weightList
    }
}