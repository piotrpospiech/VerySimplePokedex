package com.example.pokefinder.di.module

import androidx.room.Room
import com.example.pokefinder.app.ApplicationPokemon
import com.example.pokefinder.model.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(): PokemonDatabase {
        return Room.databaseBuilder(ApplicationPokemon.instance, PokemonDatabase::class.java, "pokemon_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}