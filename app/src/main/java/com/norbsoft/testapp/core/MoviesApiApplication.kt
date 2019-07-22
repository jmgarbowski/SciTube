package com.norbsoft.testapp.core

import android.app.Application
import com.norbsoft.testapp.di.appModule
import com.norbsoft.testapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, networkModule))
        }
    }

}