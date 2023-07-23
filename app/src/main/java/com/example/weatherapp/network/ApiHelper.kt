package com.example.weatherapp.network

import com.example.weatherapp.model.Weather
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiHelper {
    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&daily=sunrise,sunset&timezone=GMT")
    suspend fun requestToWeather() : Weather?
}