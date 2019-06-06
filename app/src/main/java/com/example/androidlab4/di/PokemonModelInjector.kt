package com.example.androidlab4.di

import com.example.androidlab4.model.PokemonModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ModelInjector {

    fun inject(pokemonModel: PokemonModel)

    @Component.Builder
    interface Builder {
        fun build(): ModelInjector

        fun networkModule(networkModule: NetworkModule): Builder

    }
}