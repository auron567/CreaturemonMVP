package com.example.creaturemonmvp.app

import android.app.Application
import com.example.creaturemonmvp.di.AppComponent
import com.example.creaturemonmvp.di.DaggerAppComponent

class CreaturemonApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}