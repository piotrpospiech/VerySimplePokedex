package com.example.androidlab4.di.module

import com.example.androidlab4.model.ApiServiceInterface
import dagger.Module
import dagger.Provides

@Module
class PokemonModelModule {

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }

}