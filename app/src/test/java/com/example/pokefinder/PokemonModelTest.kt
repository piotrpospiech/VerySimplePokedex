package com.example.pokefinder

import com.example.pokefinder.model.PokemonModel
import com.example.pokefinder.presenter.SearchPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito.*
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
        verify(searchPresenter, never())
            .onFinished(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), eq(ArrayList()), eq(0))
    }
}