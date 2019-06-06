package com.example.androidlab4

import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.presenter.SearchPresenter
import com.example.androidlab4.view.SearchView
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