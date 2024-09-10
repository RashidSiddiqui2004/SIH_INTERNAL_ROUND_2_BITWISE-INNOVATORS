package com.example.beachapp.model

data class ApiResponse (
    val forecast_date: String,
    val forecast_time: String,
    val wave_height: String,
    val wave_period: String,
    val wave_direction: String,
    val rcr: String,
    val tidal_elevation: String,
    val beach_number: String,
    val beach_name: String
)
