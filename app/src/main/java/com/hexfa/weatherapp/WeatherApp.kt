package com.hexfa.weatherapp

import androidx.multidex.MultiDexApplication
import com.hexfa.weatherapp.di.NetModule
import com.hexfa.weatherapp.di.PersistenceModule
import com.hexfa.weatherapp.di.appModule
import com.hexfa.weatherapp.di.repositoryModule
import com.hexfa.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

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


