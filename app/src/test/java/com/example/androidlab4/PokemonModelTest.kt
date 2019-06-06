package com.example.androidlab4

import com.example.androidlab4.model.PokemonModel
import com.example.androidlab4.presenter.SearchPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PokemonModelTest {

    private lateinit var pokemonModel: PokemonModel
    @Mock
    private lateinit var searchPresenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        pokemonModel = PokemonModel()
    }

    @Test
    fun searchPokemon_NullString_NoSearch() {
        pokemonModel.searchPokemon(searchPresenter, null)
        verify(searchPresenter, never()).onFinished("", "", "", ArrayList(), 0)
    }
}