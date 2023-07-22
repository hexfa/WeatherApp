package com.example.weatherapp.di

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.presentation.WeatherVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single {WeatherRepository()}

    viewModel {
        WeatherVm(get())
    }
}