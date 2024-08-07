package com.hexfa.weatherapp.ui

import com.hexfa.weatherapp.model.local.WeatherTotalInfo

data class WeatherState(
    val weatherInfo: WeatherTotalInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
