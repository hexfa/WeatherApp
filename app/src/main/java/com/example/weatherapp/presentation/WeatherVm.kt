package com.example.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.model.Weather
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.utils.ResourceState
import com.example.weatherapp.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WeatherVm constructor(private val weatherRepository: WeatherRepository) : ViewModel() {
    private var weatherResource = Resource<Weather>(State.LOADING, null, null)
    private val weatherResourceState = ResourceState(weatherResource)

    fun requestToWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.requestToWeather().catch {
                Log.d("responsedd",it.toString())
               weatherResource = Resource(State.ERROR,it.message.orEmpty(),null)
                Resource.error<Throwable>(it.message)
                weatherResourceState.value = weatherResource
            }.collect { weather ->
                Log.d("responsedd","weather.toString()")

                weatherResource = if (weather == null) {
                    weatherResource = Resource(State.ERROR,"An error accrued",null)

                    Resource.error("An error accrued")
                } else {
                    weatherResource = Resource(State.SUCCESS,"success",weather)
                    Resource.success(weather)
                }
                weatherResourceState.value = weatherResource
            }
        }
    }

    fun insertWeatherInDB(weather: Weather?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (weather != null)
                weatherRepository.insert(weather)
        }
    }

    fun weatherResourceState() = weatherResourceState
}