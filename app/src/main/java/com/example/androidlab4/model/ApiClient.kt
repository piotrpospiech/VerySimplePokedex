package com.example.androidlab4.model

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"
    private var retrofit: Retrofit? = null


    fun getClient(): Retrofit? {
        if (retrofit == null) {
            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}