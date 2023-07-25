package com.example.weatherapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.local.WeatherDataHourly
import com.example.weatherapp.ui.theme.LightBlue
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherDataHourly,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    val formattedTime = remember(weatherData) {
        weatherData.time?.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Card(

        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(1.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightBlue,
        )    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formattedTime!!,
                color = Color.LightGray,
                fontFamily = FontFamily(Font(R.font.safont)),
                )
            Image(
                painter = painterResource(id = weatherData.weatherType!!.iconRes),
                contentDescription = null,
                modifier = Modifier.width(60.dp)
            )
            Text(
                text = "${weatherData.temperatureCelsius}°C",
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.safont)),
                )

        }
    }
}