package com.example.pokefinder.model

import com.example.pokefinder.model.Pokemon.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceInterface {

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>

}