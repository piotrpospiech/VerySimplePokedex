package com.example.androidlab4.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.androidlab4.R
import com.example.androidlab4.di.component.DaggerActivityComponent
import com.example.androidlab4.di.module.ActivityModule
import com.example.androidlab4.model.Pokemon.Type
import com.example.androidlab4.presenter.SearchPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        injectDependency()

        presenter.attach(this)

        searchButton.setOnClickListener {
            presenter.searchPokemon(pokemonNameEditText.text.toString())
        }
    }

    override fun updatePokemon(frontUrl: String?, backUrl: String?, name: String?, types: List<Type>?, weight: Int?) {
        updateImage(frontUrl, frontPokemonImageView)
        updateImage(backUrl, backPokemonImageView)
        updateData(name, types, weight)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    private fun updateImage(imgUrl: String?, imageView: ImageView) {
        Picasso.get()
            .load(imgUrl)
            .resize(400, 400)
            .centerCrop()
            .into(imageView)
    }

    private fun updateData(name: String?, types: List<Type>?, weight: Int?) {
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
