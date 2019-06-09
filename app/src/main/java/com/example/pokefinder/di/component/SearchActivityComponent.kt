package com.example.pokefinder.di.component

import com.example.pokefinder.presenter.SearchPresenter
import com.example.pokefinder.view.SearchActivity
import dagger.Component


@Component
interface SearchActivityComponent {

    fun getSearchPresenter(): SearchPresenter

    fun inject(searchActivity: SearchActivity)

}

