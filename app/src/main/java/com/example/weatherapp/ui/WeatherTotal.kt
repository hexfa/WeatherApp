package com.example.weatherapp.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.DarkBlue
import com.example.weatherapp.R
import com.example.weatherapp.data.mappers.IndexedWeatherDataDaily
import com.example.weatherapp.model.local.WeatherDataDaily
import java.time.LocalDateTime

import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@Composable
fun WeatherTotal(
    state: WeatherState,

) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        state.weatherInfo.weatherDateDaily.let {
            var today: IndexedWeatherDataDaily = it?.get(0) ?: IndexedWeatherDataDaily(1,
                WeatherDataDaily(LocalDateTime.MAX, LocalDateTime.MIN)
            )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Today ${
                                data.time?.format(
                                    DateTimeFormatter.ofPattern("HH:mm")
                                )
                            }",
                            modifier = Modifier.align(Alignment.TopStart),
                            color = DarkBlue,
                            fontFamily = FontFamily(Font(R.font.safont))
                        )
                        Image(
                            painter = painterResource(id = data.weatherType?.iconRes?:R.drawable.cloudy_day_1),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().height(300.dp)
                        )

                        Text(
                            text = "${data.temperatureCelsius}°C",
                            fontSize = 50.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.safont)),
                            modifier = Modifier.align(Alignment.BottomCenter),
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = data.weatherType?.weatherDesc?:"There is some problem.",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.safont))
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherDataDisplay(
                            value = data.pressure?.roundToInt()?:1,
                            unit = "hpa",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                        WeatherDataDisplay(
                            value = data.humidity?:1,
                            unit = "%",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                        WeatherDataDisplay(
                            value = data.windSpeed?.roundToInt()?:1,
                            unit = "km/h",
                            icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherSunriseSunset(
                            unit = " ${
                                today.data.sunrise.format(
                                    DateTimeFormatter.ofPattern("HH:mm")
                                )
                            }",
                            icon = ImageVector.vectorResource(id = R.drawable.sunrise_svgrepo_com__1_),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )
                        WeatherSunriseSunset(
                            unit = " ${
                                today.data.sunset.format(
                                    DateTimeFormatter.ofPattern("HH:mm")
                                )
                            }",
                            icon = ImageVector.vectorResource(id = R.drawable.sunset_svgrepo_com),
                            iconTint = Color.White,
                            textStyle = TextStyle(color = Color.White)
                        )

                    }
                }
            }

    }
}
