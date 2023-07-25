package com.example.weatherapp.model.local
import java.time.LocalDateTime

data class WeatherDataHourly(
    val time: LocalDateTime?,
    val temperatureCelsius: Double?,
    val pressure: Double?,
    val windSpeed: Double?,
    val humidity: Int?,
    val weatherType: WeatherType?,
)
