package com.example.androidlab4.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.example.androidlab4.R
import com.example.androidlab4.adapter.PokemonDataAdapter
import com.example.androidlab4.di.component.DaggerActivityComponent
import com.example.androidlab4.di.module.ActivityModule
import com.example.androidlab4.presenter.SearchPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var pokemonDataAdapter: PokemonDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        injectDependency()
        setupDataList()

        presenter.attach(this)

        searchButton.setOnClickListener {
            presenter.searchPokemon(pokemonNameEditText.text.toString())
        }
    }

    override fun updatePokemon(frontUrl: String?, backUrl: String?, pokemonData: ArrayList<ArrayList<String>>) {
        updateImage(frontUrl, frontPokemonImageView)
        updateImage(backUrl, backPokemonImageView)

        pokemonNameEditText.text.clear()
        if(frontUrl.isNullOrBlank()) {
            dataConstraintLayout.visibility = View.GONE
        }
        else {
            dataConstraintLayout.visibility = View.VISIBLE
            pokemonDataAdapter.updateData(pokemonData)
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    private fun setupDataList() {
        dataListRecyclerView.layoutManager = LinearLayoutManager(this)
        pokemonDataAdapter = PokemonDataAdapter(ArrayList(), this)
        dataListRecyclerView.adapter = pokemonDataAdapter
    }

    private fun updateImage(imgUrl: String?, imageView: ImageView) {
        Picasso.get()
            .load(imgUrl)
            .resize(400, 400)
            .centerCrop()
            .into(imageView)
    }
}
