package com.example.weatherapp.presentation

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
        viewModelScope.launch {
            weatherRepository.requestToWeather().catch {
                weatherResource = Resource.error(it.message)
                weatherResourceState.value = weatherResource
            }.collect { weather ->
                weatherResource = if (weather == null) {
                    Resource.error("An error accrued")
                } else {
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