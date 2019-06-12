package com.example.pokefinder.di.module

import com.example.pokefinder.model.PokemonModel
import dagger.Module
import dagger.Provides

@Module
object PokemonModelModule {

    @Provides
    fun providePokemonModel(): PokemonModel {
        return PokemonModel()
    }

}