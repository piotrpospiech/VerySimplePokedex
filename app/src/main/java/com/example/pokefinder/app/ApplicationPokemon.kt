package com.example.pokefinder.app

import android.app.Application
import com.facebook.stetho.Stetho

class ApplicationPokemon: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        lateinit var instance: ApplicationPokemon private set
    }

}