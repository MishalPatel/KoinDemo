package com.mishal.koindemo.di

import com.mishal.koindemo.data.rest.RepoService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(RepoService::class.java) }
}