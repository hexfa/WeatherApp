package com.hexfa.weatherapp.data.mappers


import android.os.Build
import androidx.annotation.RequiresApi
import com.hexfa.weatherapp.model.local.WeatherTotalInfo
import com.hexfa.weatherapp.model.remote.Daily
import com.hexfa.weatherapp.model.remote.Hourly
import com.hexfa.weatherapp.model.remote.Weather
import com.hexfa.weatherapp.model.local.WeatherDataDaily
import com.hexfa.weatherapp.model.local.WeatherDataHourly
import com.hexfa.weatherapp.model.local.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherDataHourly(
    val index: Int,
    val data: WeatherDataHourly
)
 data class IndexedWeatherDataDaily(
    val index: Int,
    val data: WeatherDataDaily
)

@RequiresApi(Build.VERSION_CODES.O)
fun Hourly.toWeatherDataMap(): Map<Int, List<WeatherDataHourly>> ?{
    return time?.mapIndexed { index, time ->
        val temperature = temperatures?.get(index)
        val weatherCode = weatherCodes?.get(index)
        val windSpeed = windSpeeds?.get(index)
        val pressure = pressureMsl?.get(index)
        val humidity = relativehumidity2m?.get(index)
        IndexedWeatherDataHourly(
            index = index,
            data = WeatherDataHourly(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = weatherCode?.let { WeatherType.fromWMO(it) },
            )
        )
    }?.groupBy {
        it.index / 24
    }?.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Daily.toWeatherDateMapDaily(): List<IndexedWeatherDataDaily>? {
    return time?.let {
        List(it.size) { index ->
        val sunrise= LocalDateTime.parse(sunrise?.get(index) ?:"" , DateTimeFormatter.ISO_DATE_TIME)
        val sunset= LocalDateTime.parse(sunset?.get(index) ?:"" , DateTimeFormatter.ISO_DATE_TIME)
        IndexedWeatherDataDaily(index, WeatherDataDaily(sunset = sunset,sunrise=sunrise))
    }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Weather.toWeatherInfo(): WeatherTotalInfo {
    val weatherDataHourly = hourly?.toWeatherDataMap()
    val weatherDataDaily=daily?.toWeatherDateMapDaily()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataHourly?.get(0)?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time?.hour == hour
    }
    return WeatherTotalInfo(
        weatherDataPerDay = weatherDataHourly,
        weatherDateDaily=weatherDataDaily,
        currentWeatherData = currentWeatherData
    )
}
