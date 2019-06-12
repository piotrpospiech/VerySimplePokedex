package com.example.pokefinder.di.component

import com.example.pokefinder.di.module.PokemonModelModule
import com.example.pokefinder.presenter.SearchPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PokemonModelModule::class])
interface SearchPresenterComponent {

    fun inject(searchPresenter: SearchPresenter)

    @Component.Builder
    interface Builder {
        fun build(): SearchPresenterComponent

        fun modelModule(pokemonModelModule: PokemonModelModule): Builder
    }

}