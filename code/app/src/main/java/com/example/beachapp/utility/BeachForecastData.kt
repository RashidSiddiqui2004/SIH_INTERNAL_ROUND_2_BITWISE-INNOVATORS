package com.example.beachapp.utility

import android.content.Context
import android.util.Log
import com.example.beachapp.model.ApiResponse
import com.example.beachapp.model.ForecastData
import com.example.beachapp.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object BeachForecastData {

    private var repo = RetrofitClient.beachApiService

    var beachIdToApiResponseData: MutableMap<Int, MutableList<ApiResponse>> = hashMapOf()
    var beachIdToForecastData: MutableMap<Int, MutableList<ForecastData>> = hashMapOf()

    fun fetchDataFromApi(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            for (key in BeachMap.beachIdToBeach.keys) {
                Log.d("FETCHING DATA", "Fetching data for Beach ID: $key")
                repo.getClimaticConditions(key).enqueue(object : Callback<List<ApiResponse>> {
                    override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                        if (response.isSuccessful) {
                            val forecasts = response.body()
                            if (forecasts != null) {
                                beachIdToApiResponseData[key] = forecasts.toMutableList()

                                // Convert and add to forecast data map
                                forecasts.forEach { forecast ->
                                    val forecastData = ForecastData(
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
                                    beachIdToForecastData.getOrPut(key) { mutableListOf() }.add(forecastData)
                                    addIntoSafeAndUnsafeBeaches(key)
                                    Log.d("DATAKK", forecastData.toString())
                                }
                            }
                        } else {
                            Log.e("API Error", "Error response code: ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                        Log.e("API Error", "Failed to fetch data for Beach ID: $key", t)
                    }
                })
            }
        }
    }
    suspend fun getForABeach(id: Int): MutableList<ForecastData> = suspendCancellableCoroutine { continuation ->
        val data = mutableListOf<ForecastData>()

        repo.getClimaticConditions(id).enqueue(object : Callback<List<ApiResponse>> {
            override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                if (response.isSuccessful) {
                    val forecasts = response.body()
                    if (forecasts != null) {
                        forecasts.forEach { forecast ->
                            val forecastData = ForecastData(
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
                            data.add(forecastData)
                        }
                        // Resume the coroutine with the collected data
                        continuation.resume(data)
                    } else {
                        // Resume with empty data if forecasts are null
                        continuation.resume(data)
                    }
                } else {
                    Log.e("API Error", "Error response code: ${response.code()}")
                    continuation.resume(data) // Resume with empty data on error
                }
            }

            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
                Log.e("API Error", "Failed to fetch data for Beach ID: $id", t)
                continuation.resumeWithException(t) // Resume with the exception
            }
        })
    }


    private fun addIntoSafeAndUnsafeBeaches(beachId: Int) {

        var rcr = BeachForecastData.beachIdToForecastData.get(beachId)?.get(0)?.rcr

        if (rcr == null || rcr < 0.45) {
            BeachMap.beachIdToBeach.get(beachId)?.let { SafeAndUnsafeBeaches.safeBeaches.add(it) }
        } else {
            BeachMap.beachIdToBeach.get(beachId)?.let { SafeAndUnsafeBeaches.unSafeBeaches.add(it) }
        }

    }
}


//package com.example.beachapp.utility
//
//import android.content.Context
//import android.util.Log
//import com.example.beachapp.model.ApiResponse
//import com.example.beachapp.model.ForecastData
//import com.example.beachapp.retrofit.RetrofitClient
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//object BeachForecastData {
//
//    private var repo = RetrofitClient.beachApiService
//
//    var beachIdToApiResponseData : MutableMap<Int, MutableList<ApiResponse>> = hashMapOf()
//
//    var beachIdToForecastData : MutableMap<Int, MutableList<ForecastData>> = hashMapOf()
//
//    fun fetchDataFromApi(context : Context) {
//        CoroutineScope(Dispatchers.IO).launch {
//            for (key in BeachMap.beachIdToBeach.keys) {
//                Log.d("FETCHING DATA", key.toString())
//                repo.getClimaticConditions(key)
//            }
//        }
//    }
//}