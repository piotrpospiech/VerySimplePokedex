package com.example.pokefinder.di.module

import com.example.pokefinder.presenter.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
object SearchPresenterModule {

    @Provides
    fun provideSearchPresenter(): SearchPresenter {
        return SearchPresenter()
    }
}