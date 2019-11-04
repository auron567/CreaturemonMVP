package com.example.creaturemonmvp.app

import android.app.Application
import com.example.creaturemonmvp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CreaturemonApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CreaturemonApplication)
            modules(appModule)
        }
    }
}