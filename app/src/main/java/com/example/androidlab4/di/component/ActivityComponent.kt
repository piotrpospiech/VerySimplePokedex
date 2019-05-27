package com.example.androidlab4.di.component

import com.example.androidlab4.di.module.ActivityModule
import com.example.androidlab4.view.SearchActivity
import dagger.Component


@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(searchActivity: SearchActivity)
}