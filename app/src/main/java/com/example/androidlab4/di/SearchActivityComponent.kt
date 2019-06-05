package com.example.androidlab4.di

import com.example.androidlab4.presenter.SearchPresenter
import com.example.androidlab4.view.SearchActivity
import dagger.Component


@Component
interface SearchActivityComponent {

    fun getSearchPresenter(): SearchPresenter

    fun inject(searchActivity: SearchActivity)
}