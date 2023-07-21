package com.example.weatherapp.data

import com.example.weatherapp.model.Weather
import com.example.weatherapp.network.ApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository constructor(private val apiHelper: ApiHelper): IRepository<Weather> {

    override fun getAll(): Flow<List<Weather>> = flow {
            emit(arrayListOf())
    }

    override fun get(id: Int): Flow<Weather> = flow {

    }

    override fun delete(id: Int) {

    }

    override fun deleteAll() {

    }

    override fun update(weather: Weather) {

    }

    override fun insert(vararg weather: Weather) {

    }

    fun requestToWeather() : Flow<Weather?> = flow {
        emit(apiHelper.requestToWeather())
    }
}