package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.presentation.WeatherVm
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single {WeatherRepository()}
    //single<Context> { androidContext() }

    viewModel {
        WeatherVm(get())
    }
}