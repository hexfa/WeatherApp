package com.example.weatherapp.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.weatherapp.utils.State

@Composable
fun WeatherScreen(vm: WeatherVm) {
    val resource=vm.weatherResourceState().value

    when(resource.state){
        State.LOADING->{
            Text(text = "Loading...")
        }
        State.SUCCESS->{
            val weather=resource.data
            Text(text = "elevation : ${weather?.toString()}")
            vm.insertWeatherInDB(weather)
        }
        State.ERROR->{
            Text(text = "${resource.msg}")
        }
    }
}