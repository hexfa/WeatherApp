package com.example.weatherapp.presentation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.weatherapp.utils.State

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherScreen(vm: WeatherVm) {
    val resource = vm.weatherResourceState().value
    val isRefreshing = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    when (resource.state) {
        State.LOADING -> {
            Text(text = "Loading...")
        }

        State.SUCCESS -> {
            val weather = resource.data
            Text(text = "elevation : ${weather?.toString()}")
            vm.insertWeatherInDB(weather)
        }

        State.ERROR -> {
            Text(text = "${resource.msg}")
        }
    }
}