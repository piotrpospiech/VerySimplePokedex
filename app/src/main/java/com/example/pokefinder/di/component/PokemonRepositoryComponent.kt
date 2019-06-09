package com.example.pokefinder.di.component

import com.example.pokefinder.di.module.DatabaseModule
import com.example.pokefinder.model.db.PokemonRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface PokemonRepositoryComponent {

    fun inject(pokemonRepository: PokemonRepository)

    @Component.Builder
    interface Builder {
        fun build(): PokemonRepositoryComponent

        fun databaseModule(databaseModule: DatabaseModule): Builder
    }
}