package com.example.weatherapp.network

import com.example.weatherapp.model.remote.Weather
import retrofit2.Response
import retrofit2.http.GET

interface ApiHelper {
    @GET("v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&daily=sunrise,sunset&timezone=GMT")
    suspend fun requestToWeather() : Response<Weather?>
}