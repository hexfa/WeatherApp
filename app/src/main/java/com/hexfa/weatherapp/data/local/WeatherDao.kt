package com.hexfa.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hexfa.weatherapp.model.remote.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherItem(weather: Weather)

    @Query("SELECT * from `weather`")
    suspend fun getWeather(): Weather
}