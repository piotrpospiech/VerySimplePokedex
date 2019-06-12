package com.example.pokefinder.di.component

import com.example.pokefinder.di.module.ApiModule
import com.example.pokefinder.di.module.RepositoryModule
import com.example.pokefinder.model.PokemonModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class])
interface PokemonModelComponent {

    fun inject(pokemonModel: PokemonModel)

    @Component.Builder
    interface Builder {
        fun build(): PokemonModelComponent

        fun apiModule(apiModule: ApiModule): Builder

        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}