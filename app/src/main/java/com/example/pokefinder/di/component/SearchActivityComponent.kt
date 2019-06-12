package com.example.pokefinder.di.component

import com.example.pokefinder.di.module.SearchPresenterModule
import com.example.pokefinder.presenter.SearchPresenter
import com.example.pokefinder.view.SearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SearchPresenterModule::class])
interface SearchActivityComponent {

    fun inject(searchActivity: SearchActivity)

    @Component.Builder
    interface Builder {
        fun build(): SearchActivityComponent

        fun presenterBuilder(searchPresenterModule: SearchPresenterModule): Builder
    }

}