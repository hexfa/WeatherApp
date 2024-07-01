package com.hexfa.weatherapp.network

import com.hexfa.weatherapp.model.remote.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {
    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl&daily=sunrise,sunset&timezone=GMT")
    suspend fun requestToWeather(@Query("latitude") lat: Double,
                                 @Query("longitude") long: Double) : Response<Weather?>
}