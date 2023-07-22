package com.example.weatherapp

import androidx.multidex.MultiDexApplication
import com.example.weatherapp.di.NetModule
import com.example.weatherapp.di.PersistenceModule
import com.example.weatherapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

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

        }}
}

