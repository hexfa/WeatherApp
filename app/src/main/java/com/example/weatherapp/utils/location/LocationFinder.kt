package com.example.weatherapp.utils.location

import android.location.Location

interface LocationFinder {
    suspend fun getCurrentLocation(): Location?

}