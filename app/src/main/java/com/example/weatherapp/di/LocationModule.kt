package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.utils.location.LocationFinder
import com.example.weatherapp.utils.location.LocationFinderImp
import com.google.android.gms.location.FusedLocationProviderClient
import org.koin.dsl.binds
import org.koin.dsl.module

val locationModule = module {
    // تعریف اعتبارسنجی از نوع DefaultGeoTrace و اتصال آن به GeoTrace
    // به عبارت دیگر DefaultGeoTrace به عنوان GeoTrace تزریق می‌شود.
    single { provideLocationFinder(get(),get()) }

}

private fun provideLocationFinder( context: Context,locationClient: FusedLocationProviderClient): LocationFinder {
    // You can use 'someDependency' to construct the DefaultGeoTrace instance
    return LocationFinderImp(context,locationClient)
}