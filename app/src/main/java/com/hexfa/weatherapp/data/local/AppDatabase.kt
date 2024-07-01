package com.hexfa.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hexfa.weatherapp.model.remote.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

}