package com.example.androidlab4.di.component

import com.example.androidlab4.di.module.PokemonModelModule
import dagger.Component

@Component(modules = [PokemonModelModule::class])
interface PokemonModelComponent {
    fun inject(pokemonModelModule: PokemonModelModule)
}