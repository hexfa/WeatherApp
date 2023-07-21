package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WeatherVm constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun requestToWeather(){
        viewModelScope.launch(Dispatchers.IO){
            weatherRepository.requestToWeather().catch {

            }.collect{

            }
        }
    }
}