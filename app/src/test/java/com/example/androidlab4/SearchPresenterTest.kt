package com.example.androidlab4

import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.presenter.SearchPresenter
import com.example.androidlab4.utils.Constants
import com.example.androidlab4.view.SearchView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
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
        searchPresenter.attach(view, pokemonModel)
    }

    @Test
    fun searchPokemon_EmptyString_NoSearch() {
        searchPresenter.searchPokemon("")
        verify(pokemonModel, never()).searchPokemon(searchPresenter, "")
    }

    @Test
    fun searchPokemon_NullString_NoSearch() {
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
        Assert.assertEquals(secretString.length, Constants.SECRET_STRING_LENGHT)
        verify(view).showSecretToast()
    }



}