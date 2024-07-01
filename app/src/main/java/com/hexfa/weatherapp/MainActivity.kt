package com.hexfa.weatherapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import com.hexfa.weatherapp.presentation.WeatherVm
import com.hexfa.weatherapp.ui.WeatherForecast
import com.hexfa.weatherapp.ui.WeatherTotal
import com.hexfa.weatherapp.ui.theme.DarkBlue
import com.hexfa.weatherapp.ui.theme.LightBlue
import com.hexfa.weatherapp.ui.theme.WeatherAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    private val vm: WeatherVm by viewModel()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    private val enableLocationActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Log.d("resultCode", it.data.toString())
            if (checkLocationPermissions())
                vm.requestToWeather()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!isGpsEnabled) {
                enableLocation()
        }
        Log.d("resultCode", "it.data.toString()")

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                vm.requestToWeather()
            }
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
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

    private fun checkLocationPermissions(): Boolean {
        val fineLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationPermission =
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        return fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                coarseLocationPermission == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun enableLocation() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        enableLocationActivityResultLauncher.launch(intent)
    }
}
