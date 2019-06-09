package com.example.pokefinder

import com.example.pokefinder.model.PokemonModel
import com.example.pokefinder.presenter.SearchPresenter
import com.example.pokefinder.view.SearchView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SearchPresenterTest {

    private lateinit var searchPresenter: SearchPresenter
    @Mock
    private lateinit var view: SearchView
    @Mock
    private lateinit var pokemonModel: PokemonModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        searchPresenter = SearchPresenter()
        searchPresenter.setup(view)
    }

    @Test
    fun searchPokemon_EmptyString_NoSearch() {
        searchPresenter.searchPokemon("")
        verify(pokemonModel, never()).searchPokemon(searchPresenter, "")
    }

    @Test
    fun searchPokemon_NullString_NoSearch() {
        //`when`(pokemonModel.searchPokemon()).then()
        searchPresenter.searchPokemon(null)
        verify(pokemonModel, never()).searchPokemon(searchPresenter, null)
    }

    @Test
    fun searchPokemon_NormalString_ShouldSearch() {
        val exampleName = "charmander"
        searchPresenter.searchPokemon(exampleName)
        verify(pokemonModel).searchPokemon(searchPresenter, exampleName)
    }

    @Test
    fun searchPokemon_SecretString_ShouldWork() {
        val secretString = "123456789012345678901234567"
        searchPresenter.searchPokemon(secretString)
        verify(view).showSecretToast()
    }



}