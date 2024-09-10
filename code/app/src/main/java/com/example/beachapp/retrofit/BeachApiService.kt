package com.example.beachapp.api

import com.example.beachapp.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BeachApiService {
    @GET("fetchRipForecast")
    fun getClimaticConditions(
        @Query("beachId") beachId: Int
    ): Call<List<ApiResponse>>
}
