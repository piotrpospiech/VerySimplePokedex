package com.example.androidlab4.di.component

import com.example.androidlab4.di.module.ApiModule
import com.example.androidlab4.model.PokemonModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface PokemonModelComponent {

    fun inject(pokemonModel: PokemonModel)

    @Component.Builder
    interface Builder {
        fun build(): PokemonModelComponent

        fun apiModule(apiModule: ApiModule): Builder
    }
}