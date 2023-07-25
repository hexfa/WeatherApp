package com.example.weatherapp.data

import com.example.weatherapp.data.local.WeatherDao
import com.example.weatherapp.model.remote.Weather
import com.example.weatherapp.network.ApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class WeatherRepository : IRepository<Weather>,KoinComponent {
    private val apiHelper: ApiHelper by inject()
    private val weatherDao: WeatherDao by inject()

    override fun get(): Flow<Weather> = flow {
        emit(weatherDao.getWeather())
    }

    override suspend fun delete(id: Int) {

    }

    override suspend fun update(weather: Weather) {

    }

    override suspend fun insert(weather: Weather) = weatherDao.insertWeatherItem(weather)

    fun requestToWeather(lat:String,long:String) : Flow<Response<Weather?>> = flow {
        emit(apiHelper.requestToWeather(createQueryMap(lat , long)))
    }

    private fun createQueryMap(lat:String,long:String): Map<String, String> {
        val queryMap= mutableMapOf<String,String>()

        return queryMap.apply {
            this["latitude"]=lat
            this["longitude"]=long
            this["hourly"]="temperature_2m,relativehumidity_2m,pressure_msl,surface_pressure"
            this["daily"]="sunrise,sunset"
            this["timezone"]="GMT"
        }
    }
}