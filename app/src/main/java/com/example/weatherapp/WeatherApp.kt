package com.example.weatherapp

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.weatherapp.di.NetModule
import com.example.weatherapp.di.PersistenceModule
import com.example.weatherapp.di.appModule
import com.example.weatherapp.di.repositoryModule
import com.example.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


import org.koin.dsl.module

class WeatherApp : MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        initKoinDi()

    }
    private fun initKoinDi() {

        startKoin {
            androidContext(this@WeatherApp)
            modules(NetModule.networkModule)
            modules(PersistenceModule.persistenceModule)
            modules(appModule)
            modules(viewModelModule)
            modules(repositoryModule)



        }}
}


