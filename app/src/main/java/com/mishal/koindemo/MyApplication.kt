package com.mishal.koindemo

import android.app.Application
import com.mishal.koindemo.di.apiModule
import com.mishal.koindemo.di.appModule
import com.mishal.koindemo.di.networkModule
import com.mishal.koindemo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(apiModule, appModule, networkModule, viewModelModule))
        }
    }
}