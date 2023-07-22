package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.Weather

@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

}