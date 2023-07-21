package com.example.weatherapp.network

import com.example.weatherapp.model.Weather

interface ApiHelper {
    suspend fun requestToWeather() : Weather?
}