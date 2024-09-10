// RetrofitClient.kt
package com.example.beachapp.retrofit

import com.example.beachapp.api.BeachApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://mosdac.gov.in/apibeach/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val beachApiService: BeachApiService by lazy {
        retrofit.create(BeachApiService::class.java)
    }
}
