package com.example.weatherapp.model

import com.squareup.moshi.Json

data class Weather(
	val id:Int,

	@Json(name="elevation")
	val elevation: Double? = null,

	@Json(name="hourly_units")
	val hourlyUnits: HourlyUnits? = null,

	@Json(name="generationtime_ms")
	val generationtimeMs: Double? = null,

	@Json(name="timezone_abbreviation")
	val timezoneAbbreviation: String? = null,

	@Json(name="daily_units")
	val dailyUnits: DailyUnits? = null,

	@Json(name="timezone")
	val timezone: String? = null,

	@Json(name="latitude")
	val latitude: Double? = null,

	@Json(name="daily")
	var daily: Daily? = null,

	@Json(name="utc_offset_seconds")
	val utcOffsetSeconds: Int? = null,

	@Json(name="hourly")
	val hourly: Hourly? = null,

	@Json(name="longitude")
	val longitude: Double? = null
)

data class DailyUnits(

	@Json(name="sunrise")
	val sunrise: String? = null,

	@Json(name="sunset")
	val sunset: String? = null,

	@Json(name="time")
	val time: String? = null
)

data class Daily(

	@Json(name="sunrise")
	val sunrise: List<String>? = null,

	@Json(name="sunset")
	val sunset: List<String>? = null,

	@Json(name="time")
	val time: List<String>? = null
)

data class HourlyUnits(

	@Json(name="pressure_msl")
	val pressureMsl: String? = null,

	@Json(name="temperature_2m")
	val temperature2m: String? = null,

	@Json(name="relativehumidity_2m")
	val relativehumidity2m: String? = null,

	@Json(name="surface_pressure")
	val surfacePressure: String? = null,

	@Json(name="time")
	val time: String? = null
)

data class Hourly(

	@Json(name="pressure_msl")
	val pressureMsl: List<Double>? = null,

	@Json(name="temperature_2m")
	val temperature2m: List<Double>? = null,

	@Json(name="relativehumidity_2m")
	val relativehumidity2m: List<Int>? = null,

	@Json(name="surface_pressure")
	val surfacePressure: List<Double>? = null,

	@Json(name="time")
	val time: List<String>? = null
)
