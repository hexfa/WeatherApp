package com.example.weatherapp.ui

import com.example.weatherapp.model.local.WeatherTotalInfo

data class WeatherState(
    val weatherInfo: WeatherTotalInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
