package com.example.androidlab4.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.androidlab4.R
import com.example.androidlab4.model.Api
import com.example.androidlab4.model.pokemon.Pokemon
import com.example.androidlab4.model.pokemon.Type
import com.example.androidlab4.presenter.SearchPresenter
import com.example.androidlab4.presenter.SearchView
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchView : AppCompatActivity(), SearchView {

    private val BASE_URL = "https://pokeapi.co/api/v2/"
    private lateinit var client: OkHttpClient
    private lateinit var retrofit: Retrofit
    private val presenter = SearchPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        presenter.initializeTools()

        searchButton.setOnClickListener {
            presenter.searchPokemon(pokemonNameEditText.text.toString())
        }
    }

    override fun searchPokemon(name: String) {
        val api = retrofit.create(Api::class.java)
        val call = api.getPokemon(name)

        call.enqueue(object: Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if(response.code() == 200) {
                    val pokemon = response.body()
                    updatePokemonImage(pokemon)
                    updatePokemonData(pokemon)
                }
                else {
                    Log.d("SearchView", "Failed to get pokemon")
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.d("SearchView", t.message)
            }
        })
    }

    override fun updatePokemonImage(pokemon: Pokemon?) {
        val frontUrl = pokemon?.sprites?.front_default
        val backUrl = pokemon?.sprites?.back_default

        Picasso.get()
            .load(frontUrl)
            .resize(400, 400)
            .centerCrop()
            .into(frontPokemonImageView)

        Picasso.get()
            .load(backUrl)
            .resize(400, 400)
            .centerCrop()
            .into(backPokemonImageView)
    }

    override fun updatePokemonData(pokemon: Pokemon?) {
        val name = pokemon?.name
        nameResultTextView.text = name
        pokemonNameEditText.text.clear()

        val types: List<Type>? = pokemon?.types
        val typesResult = StringBuilder()

        types?.forEach {
            typesResult.append(it.type.name)
            typesResult.append(", ")
        }
        typesResultTextView.text = typesResult

        val weight = pokemon?.weight
        weightResultTextView.text = weight.toString()

        if(name != null) dataConstraintLayout.visibility = View.VISIBLE
        else dataConstraintLayout.visibility = View.INVISIBLE
    }

    override fun initializeStetho() {
        Stetho.initializeWithDefaults(this)
        client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    override fun initializeRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
