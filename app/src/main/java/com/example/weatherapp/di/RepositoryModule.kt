package com.example.weatherapp.di

import com.example.weatherapp.data.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherRepository()}

}