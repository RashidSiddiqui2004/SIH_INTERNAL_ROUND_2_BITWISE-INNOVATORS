// BeachRepository.kt
package com.example.beachapp.repository

import android.content.Context
import android.util.Log
import com.example.beachapp.api.BeachApiService
import com.example.beachapp.model.ApiResponse
import com.example.beachapp.model.ForecastData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeachRepository(private val apiService: BeachApiService, var context : Context) {

    private var BEACH_ID = "BeachId"

    fun fetchClimaticConditions(beachId: Int) {
            // Make the API call
            apiService.getClimaticConditions(beachId)
                .enqueue(object : Callback<List<ApiResponse>> {
                    override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                        if (response.isSuccessful) {
                            val forecasts = response.body()
                            if (forecasts != null) {
                                // Process the list of beach forecasts (e.g., display in UI)
                                for (forecast in forecasts) {

//                                    val apiResponseList = BeachForecastData.beachIdToApiResponseData.getOrPut(beachId) { mutableListOf() }
//                                    apiResponseList.add(forecast)
//
//                                    var forecastData = convertIntoForecastData(forecast)
//                                    Log.d("DATAKK", forecastData.toString())
//
//                                    val forecastDataList = BeachForecastData.beachIdToForecastData.getOrPut(beachId) { mutableListOf() }
//                                    forecastDataList.add(forecastData)
                                    Log.d("Forecast Data", """
                                Date: ${forecast.forecast_date}
                                Time: ${forecast.forecast_time}
                                Wave Height: ${forecast.wave_height} meters
                                Wave Period: ${forecast.wave_period} seconds
                                Wave Direction: ${forecast.wave_direction} degrees
                                RCR: ${forecast.rcr}
                                Tidal Elevation: ${forecast.tidal_elevation} meters
                                Beach Name: ${forecast.beach_name}
                            """.trimIndent())
                                }
                            }
                        } else {
                            Log.e("API Error", "Response code: ${response.code()}")
                        }
                    }
                    override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                        Log.e("API Error", "Failed to make API call", t)
                    }
                })
    }

    private fun convertIntoForecastData(forecast: ApiResponse): ForecastData {
            var data = ForecastData(
            forecast.forecast_date,
            forecast.forecast_time,
            forecast.wave_height.toFloat(),
            forecast.wave_period.toFloat(),
            forecast.wave_direction.toFloat(),
            forecast.rcr.toFloat(),
            forecast.tidal_elevation.toFloat(),
            forecast.beach_number.toInt(),
            forecast.beach_name
        )
        return data
    }

}
