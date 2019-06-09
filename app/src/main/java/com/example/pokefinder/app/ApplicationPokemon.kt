package com.example.pokefinder.app

import android.app.Application
import com.facebook.stetho.Stetho

class ApplicationPokemon: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        instance = this
    }

    companion object {
        lateinit var instance: ApplicationPokemon private set
    }

}