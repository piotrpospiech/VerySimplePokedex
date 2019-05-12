package com.example.androidlab4.model

import com.example.androidlab4.model.pokemon.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>
}