package com.example.androidlab4.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androidlab4.R
import com.example.androidlab4.model.Pokemon.Type
import com.example.androidlab4.presenter.SearchPresenter
import com.facebook.stetho.Stetho
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchView {

    private val presenter = SearchPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        Stetho.initializeWithDefaults(this)

        searchButton.setOnClickListener {
            presenter.searchPokemon(pokemonNameEditText.text.toString())
        }
    }

    override fun updatePokemonImage(frontUrl: String?, backUrl: String?) {
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

    override fun updatePokemonData(name: String?, types: List<Type>?, weight: Int?) {
        nameResultTextView.text = name
        pokemonNameEditText.text.clear()

        val typesResult = StringBuilder()
        types?.forEach {
            typesResult.append(it.type.name)
            typesResult.append(", ")
        }
        typesResultTextView.text = typesResult

        weightResultTextView.text = weight.toString()

        if(name != null) dataConstraintLayout.visibility = View.VISIBLE
        else dataConstraintLayout.visibility = View.INVISIBLE
    }
}
