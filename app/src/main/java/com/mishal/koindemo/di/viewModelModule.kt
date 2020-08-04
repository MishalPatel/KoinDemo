package com.mishal.koindemo.di

import com.mishal.koindemo.ui.ListPostViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListPostViewModel(get()) }
}