package com.example.weatherapp.presentation

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.weatherapp.utils.State
import com.example.weatherapp.utils.location.LocationFinderImp
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WeatherScreen(vm: WeatherVm) {
    val resource=vm.weatherResourceState().value
    val activity = LocalContext.current as ComponentActivity



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