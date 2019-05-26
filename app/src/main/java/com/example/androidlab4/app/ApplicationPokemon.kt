package com.example.androidlab4.app

import android.app.Application
import com.facebook.stetho.Stetho

class ApplicationPokemon: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}