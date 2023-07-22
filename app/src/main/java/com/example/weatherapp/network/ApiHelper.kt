package com.example.weatherapp.network

import com.example.weatherapp.model.Weather
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiHelper {
    @GET("v1/forecast/")
    suspend fun requestToWeather(@QueryMap queryMap:Map<String,String>) : Weather?
}