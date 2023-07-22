package com.example.weatherapp

import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.model.mockWeatherModel
import com.example.weatherapp.network.ApiHelper
import com.example.weatherapp.presentation.WeatherVm
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class WeatherViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var mockWeatherRepository: WeatherRepository
    private lateinit var mockApiHelper: ApiHelper
    private lateinit var weatherVm: WeatherVm

    @Before
    fun init() {
        mockApiHelper = Mockito.mock(ApiHelper::class.java)
        mockWeatherRepository = WeatherRepository()
        weatherVm = WeatherVm(mockWeatherRepository)
    }

    @Test
    fun GetWeatherFromApi_RequestWeather_Weather() {
        runBlocking {
            val vm = WeatherVm(mockWeatherRepository)
            Mockito.`when`(mockApiHelper.requestToWeather()).thenReturn(mockWeatherModel)

            vm.requestToWeather()

            Assert.assertNotNull(vm.weatherResourceState().value.data)
            Assert.assertEquals(mockWeatherModel, vm.weatherResourceState().value.data)
        }
    }


    @Test
    fun InsertWeatherInDB_InsertWeather_Insert() = runBlocking {
        val vm = WeatherVm(mockWeatherRepository)
        Mockito.`when`(mockApiHelper.requestToWeather()).thenReturn(mockWeatherModel)

        vm.requestToWeather()

        val resultFromServer = vm.weatherResourceState().value.data

        if (resultFromServer != null) {
            Mockito.`when`(mockWeatherRepository.get()).thenReturn(flow {
                emit(
                    mockWeatherModel
                )
            })

            vm.insertWeatherInDB(vm.weatherResourceState().value.data)

            mockWeatherRepository.get()
                .catch {
                    it.message
                }
                .collect { weather ->
                    Assert.assertEquals(weather, vm.weatherResourceState().value.data)
                }
        }
    }
}