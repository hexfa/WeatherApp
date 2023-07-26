package com.example.weatherapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.presentation.WeatherVm
import com.example.weatherapp.ui.WeatherForecast
import com.example.weatherapp.ui.WeatherTotal
import com.example.weatherapp.ui.theme.DarkBlue
import com.example.weatherapp.ui.theme.LightBlue
import com.example.weatherapp.ui.theme.WeatherAppTheme
import org.koin.android.ext.android.inject

private const val TAG = "MainActivity"


class MainActivity : ComponentActivity() {
    private val vm: WeatherVm by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.requestToWeather()
        setContent {
            WeatherAppTheme {
                val colors = listOf(Color.White, LightBlue, DarkBlue)


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = Brush.verticalGradient(colors))

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        WeatherTotal(
                            state = vm.state,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = vm.state)
                    }
                    if (vm.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    vm.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}