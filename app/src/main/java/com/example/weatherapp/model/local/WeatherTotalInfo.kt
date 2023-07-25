package com.example.weatherapp.model.local

import com.example.weatherapp.data.mappers.IndexedWeatherDataDaily

data class WeatherTotalInfo(
    val weatherDataPerDay: Map<Int, List<WeatherDataHourly>>?,
    val weatherDateDaily: List<IndexedWeatherDataDaily>?,
    val currentWeatherData: WeatherDataHourly?
)
