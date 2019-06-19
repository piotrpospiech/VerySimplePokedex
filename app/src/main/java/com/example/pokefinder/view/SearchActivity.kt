package com.example.pokefinder.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.pokefinder.R
import com.example.pokefinder.adapter.PokemonDataAdapter
import com.example.pokefinder.di.component.DaggerSearchActivityComponent
import com.example.pokefinder.di.component.SearchActivityComponent
import com.example.pokefinder.di.module.SearchPresenterModule
import com.example.pokefinder.presenter.SearchPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast
import javax.inject.Inject

open class SearchActivity : AppCompatActivity(), SearchView {

    private var component: SearchActivityComponent = DaggerSearchActivityComponent
        .builder()
        .presenterBuilder(SearchPresenterModule)
        .build()

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var pokemonDataAdapter: PokemonDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        injectDependency()
        setupDataList()
        presenter.setup(this)

        searchButton.setOnClickListener {
            presenter.searchPokemon(pokemonNameEditText.text.toString())
        }
    }

    override fun updatePokemon(frontUrl: String?, backUrl: String?, pokemonData: ArrayList<ArrayList<String>>?) {
        updateImage(frontUrl, frontPokemonImageView)
        updateImage(backUrl, backPokemonImageView)

        pokemonNameEditText.text.clear()
        if(!frontUrl.isNullOrBlank()) {
            pokemonImageLinearLayout.visibility = View.VISIBLE
            pokemonDataAdapter.updateData(pokemonData)
        }
    }

    override fun showToast(message: String) {
        toast(message)
    }

    private fun injectDependency() {
        component.inject(this)
    }

    private fun setupDataList() {
        dataListRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
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
