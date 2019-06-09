package com.example.androidlab4.di.component

import com.example.androidlab4.di.module.DatabaseModule
import com.example.androidlab4.model.db.PokemonRepository
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