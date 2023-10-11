package com.example.cleanarchitecture.presentation

import android.app.Application
import com.example.cleanarchitecture.di.koinModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        /*
        // Initialize Koin
        If you want to switch to Koin!
        startKoin {
            androidContext(this@CustomApplication)
            modules(koinModule)
        }*/
    }
}