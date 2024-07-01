package com.hexfa.weatherapp.data

import com.hexfa.weatherapp.data.local.WeatherDao
import com.hexfa.weatherapp.model.remote.Weather
import com.hexfa.weatherapp.network.ApiHelper
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

    fun requestToWeather(lat: Double, long: Double) : Flow<Response<Weather?>> = flow {
        emit(apiHelper.requestToWeather(lat, long))
    }

   /* suspend fun requestToWeather(lat: Double, long: Double): Resource<Weather?> {
        return try {
            Resource.success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }*/

    private fun createQueryMap(lat:String,long:String): Map<String, String> {
        val queryMap= mutableMapOf<String,String>()

        return queryMap.apply {
            this["latitude"]="52.52"
            this["longitude"]="13.41"
            this["hourly"]="temperature_2m,relativehumidity_2m,pressure_msl,surface_pressure"
            this["daily"]="sunrise,sunset"
            this["timezone"]="GMT"
        }
    }
}