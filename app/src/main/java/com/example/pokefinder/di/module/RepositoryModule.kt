package com.example.pokefinder.di.module

import com.example.pokefinder.model.db.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideRepository(): PokemonRepository {
        return PokemonRepository()
    }
}