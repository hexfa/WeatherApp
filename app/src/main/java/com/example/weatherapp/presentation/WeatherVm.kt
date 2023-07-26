package com.example.weatherapp.presentation

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.mappers.toWeatherInfo
import com.example.weatherapp.model.remote.Weather
import com.example.weatherapp.ui.WeatherState
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.utils.ResourceState
import com.example.weatherapp.utils.State
import com.example.weatherapp.utils.location.LocationFinder
import com.example.weatherapp.utils.location.LocationFinderImp
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WeatherVm constructor(private val weatherRepository: WeatherRepository,private val app:Application) : AndroidViewModel(app) {
    private val locationFinder: LocationFinder= LocationFinderImp(app,LocationServices.getFusedLocationProviderClient(app))
    private var weatherResource = Resource<Weather>(State.LOADING, null, null)
    private val weatherResourceState = ResourceState(weatherResource)
    var state by mutableStateOf(WeatherState())
        private set


    @RequiresApi(Build.VERSION_CODES.O)
    fun requestToWeather(){
        viewModelScope.launch {

            state = state.copy(
                isLoading = true,
                error = null
            )

            locationFinder.getCurrentLocation()?.let {location->
                weatherRepository.
                requestToWeather(location.latitude,location.longitude).catch {
                    Log.d("responsedd",it.toString())
                    weatherResource = Resource(State.ERROR,it.message.orEmpty(),null)
                    Resource.error<Throwable>(it.message)
                    weatherResourceState.value = weatherResource
                    state = state.copy(
                        weatherInfo = null,
                        isLoading = false,
                        error = it.message
                    )
                }.collect { weather ->
                    Log.d("responsedd","weather.toString()")

                    weatherResource = if (weather == null) {
                        weatherResource = Resource(State.ERROR,"An error accrued",null)

                        Resource.error("An error accrued")
                    } else {
                        state = state.copy(
                            weatherInfo = weather.body()?.toWeatherInfo(),
                            isLoading = false,
                            error = null
                        )
                        weatherResource = Resource(State.SUCCESS,"success",weather.body())
                        Resource.success(weather.body())
                    }
                    weatherResourceState.value = weatherResource
                }
            }
        }
    }
    /*@RequiresApi(Build.VERSION_CODES.O)
    fun requestToWeather(lat:Double,long:Double) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(
                isLoading = true,
                error = null
            )
            weatherRepository.requestToWeather(lat, long).catch {
                Log.d("responsedd",it.toString())
               weatherResource = Resource(State.ERROR,it.message.orEmpty(),null)
                Resource.error<Throwable>(it.message)
                weatherResourceState.value = weatherResource
                state = state.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = it.message
                )
            }.collect { weather ->
                Log.d("responsedd","weather.toString()")

                weatherResource = if (weather == null) {
                    weatherResource = Resource(State.ERROR,"An error accrued",null)

                    Resource.error("An error accrued")
                } else {
                    state = state.copy(
                        weatherInfo = weather.body()?.toWeatherInfo(),
                        isLoading = false,
                        error = null
                    )
                    weatherResource = Resource(State.SUCCESS,"success",weather.body())
                    Resource.success(weather.body())
                }
                weatherResourceState.value = weatherResource
            }
        }
    }*/

    fun insertWeatherInDB(weather: Weather?) {
        viewModelScope.launch(Dispatchers.IO) {
            /*if (weather != null)
                weatherRepository.insert(weather)*/
        }
    }

    fun weatherResourceState() = weatherResourceState
}