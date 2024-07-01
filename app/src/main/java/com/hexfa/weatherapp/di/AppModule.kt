package com.hexfa.weatherapp.di

import com.hexfa.weatherapp.data.WeatherRepository
import com.hexfa.weatherapp.presentation.WeatherVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single{WeatherRepository()}
    /*single<Context> { androidContext() }*/

   /* factory<LocationFinder> { parametes->
        LocationFinderImp(get<Context>(),get())
    }
*/
   /* factory {
        LocationServices.getFusedLocationProviderClient(get ())
    }
    single {
        LocationFinderImp(get<Context>(),get())
    }*/
    viewModel {
        WeatherVm(get(),get())
    }
}