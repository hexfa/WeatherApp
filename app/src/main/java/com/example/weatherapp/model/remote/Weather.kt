package com.example.weatherapp.model.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
@Entity(tableName = "Weather")

data class Weather(
	@PrimaryKey(autoGenerate = true)
	var id:Int?=null,
	@Json(name="daily")
	var daily: Daily? = null,
	@field:Json(name = "hourly")
	val hourly: Hourly? = null,
	)
data class Daily(

	@Json(name="sunrise")
	val sunrise: List<String>? = null,

	@Json(name="sunset")
	val sunset: List<String>? = null,

	@Json(name="time")
	val time: List<String>? = null
)
data class Hourly(
	@Json(name="pressure_msl")
	val pressureMsl: List<Double>? = null,

	@Json(name="temperature_2m")
	val temperatures: List<Double>? = null,

	@Json(name="relativehumidity_2m")
	val relativehumidity2m: List<Int>? = null,

	@Json(name="surface_pressure")
	val surfacePressure: List<Double>? = null,

	@Json(name="time")
	val time: List<String>? = null,

	@Json(name = "weathercode")
	val weatherCodes: List<Int>,

	@Json(name = "windspeed_10m")
	val windSpeeds: List<Double>,
)
