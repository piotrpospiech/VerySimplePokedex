package com.example.androidlab4.model

import com.example.androidlab4.model.Pokemon.Pokemon
import com.example.androidlab4.utils.Constants
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceInterface {
    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>

    companion object Factory {
        fun create(): ApiServiceInterface {

            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}