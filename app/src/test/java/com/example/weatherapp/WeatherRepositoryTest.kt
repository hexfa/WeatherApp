package com.example.weatherapp

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.model.remote.mockQueryMap
import com.example.weatherapp.model.remote.mockWeatherModel
import com.example.weatherapp.network.ApiHelper
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class WeatherRepositoryTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mockApiHelper: ApiHelper
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun init() {
        mockApiHelper = Mockito.mock(ApiHelper::class.java)
        weatherRepository = WeatherRepository()
    }

    @Test
    fun InsertWeatherInDB_InsertWeather_Insert() = runBlocking {
        weatherRepository.insert(mockWeatherModel)

        weatherRepository.get()
            .catch {
                it.message
            }
            .collect { weather ->
                Assert.assertEquals(weather, mockWeatherModel)
            }
    }

    @Test
    fun GetWeatherFromApi_GetWeather_Weather() = runBlocking {
        Mockito.`when`(mockApiHelper.requestToWeather(52.52,13.41)).thenReturn( Response.success(mockWeatherModel))

        weatherRepository.requestToWeather(52.52,13.41).catch {

        }.collect { weather ->
            Assert.assertNotNull(weather)
            Assert.assertEquals(weather, mockWeatherModel)
        }
    }

    @Test
    fun UpdateWeatherInDB_UpdateWeather_Update() = runBlocking {
        var result = mockWeatherModel

        weatherRepository.get()
            .catch {
                it.message
            }.collect { weather ->
                weather.daily = null
                result = weather
                cancel()
            }

        weatherRepository.update(result)

        weatherRepository.get()
            .catch {
                it.message
            }.collect { weather ->
                Assert.assertNotEquals(weather.daily, mockWeatherModel.daily)
            }
    }

    @Test
    fun DeleteWeatherInDB_DeleteWeather_Delete() = runBlocking {
        var result = mockWeatherModel

        weatherRepository.get()
            .catch {
                it.message
            }.collect { weather ->
                result = weather
                cancel()
            }

        weatherRepository.delete(1)

        weatherRepository.get()
            .catch {
                it.message
            }.collect { weather ->
                Assert.assertNull(weather)
            }
    }

}