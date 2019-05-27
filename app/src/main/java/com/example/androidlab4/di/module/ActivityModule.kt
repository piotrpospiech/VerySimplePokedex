package com.example.androidlab4.di.module

import android.app.Activity
import com.example.androidlab4.presenter.SearchPresenter
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): SearchPresenter {
        return SearchPresenter()
    }

}