package com.hexfa.weatherapp.di

import com.hexfa.weatherapp.data.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherRepository()}

}