package com.mishal.koindemo.di

import com.example.koinmvvmretrofit.utils.SharedPreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory { SharedPreferenceManager(androidContext()) }
}