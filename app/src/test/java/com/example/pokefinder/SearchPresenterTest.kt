package com.example.pokefinder

import com.example.pokefinder.app.ApplicationPokemon
import com.example.pokefinder.model.Pokemon.Type
import com.example.pokefinder.model.Pokemon.TypeX
import com.example.pokefinder.model.PokemonModel
import com.example.pokefinder.presenter.SearchPresenter
import com.example.pokefinder.utils.Constants
import com.example.pokefinder.view.SearchView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.internal.util.reflection.FieldSetter


class SearchPresenterTest {

    private lateinit var searchPresenter: SearchPresenter
    @Mock
    private lateinit var view: SearchView
    @Mock
    private lateinit var pokemonModel: PokemonModel
    private lateinit var app: ApplicationPokemon

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        app = ApplicationPokemon()
        searchPresenter = SearchPresenter()
        searchPresenter.setup(view)
    }

    @Test
    fun searchPokemon_EmptyString_ShouldNotSearch() {
        FieldSetter.setField(searchPresenter, searchPresenter.javaClass.getDeclaredField("pokemonModel"), pokemonModel)
        searchPresenter.searchPokemon("")
        verify(pokemonModel, never()).searchPokemon(searchPresenter, "")
        verify(view).showToast(Constants.MESSAGE_WRONG_INPUT)
    }

    @Test
    fun searchPokemon_NullString_ShouldNotSearch() {
        FieldSetter.setField(searchPresenter, searchPresenter.javaClass.getDeclaredField("pokemonModel"), pokemonModel)
        searchPresenter.searchPokemon(null)
        verify(pokemonModel, never()).searchPokemon(searchPresenter, eq(anyString()))
        verify(view).showToast(Constants.MESSAGE_WRONG_INPUT)
    }

    @Test
    fun searchPokemon_NormalString_ShouldSearch() {
        FieldSetter.setField(searchPresenter, searchPresenter.javaClass.getDeclaredField("pokemonModel"), pokemonModel)
        val name = "charmander"
        searchPresenter.searchPokemon(name)
        verify(pokemonModel).searchPokemon(searchPresenter, name)
    }

    @Test
    fun onFinished_NormalData_ShouldUpdateView() {
        val frontUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png"
        val backUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/4.png"
        val list = ArrayList<ArrayList<String>>()
        list.add(arrayListOf("Name:", "Charmander"))
        list.add(arrayListOf("Types:", "Fire"))
        list.add(arrayListOf("Weight:", "8.5 kg"))

        val name = "charmander"
        val types = listOf(Type(1, TypeX("fire", "https://pokeapi.co/api/v2/type/10/")))
        val weight = 85

        searchPresenter.onFinished(frontUrl, backUrl, name, types, weight)
        verify(view).updatePokemon(frontUrl, backUrl, list)
    }

    @Test
    fun sendMessage_NormalString_ShouldUpdateView() {
        val message = "example toast message"
        searchPresenter.sendMessage(message)
        verify(view).showToast(message)
    }
}