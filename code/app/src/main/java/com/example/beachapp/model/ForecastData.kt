package com.example.beachapp.model

data class ForecastData(
    val forecast_date: String,
    val forecast_time: String,
    val wave_height: Float,
    val wave_period: Float,
    val wave_direction: Float,
    val rcr: Float,
    val tidal_elevation: Float,
    val beach_number: Int,
    val beach_name: String
)
