package com.hexfa.weatherapp.model.local

import java.time.LocalDateTime

data class WeatherDataDaily(
    val sunset: LocalDateTime,
    val sunrise: LocalDateTime
)
