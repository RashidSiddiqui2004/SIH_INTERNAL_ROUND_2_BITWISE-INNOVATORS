package com.example.beachapp.model

import com.google.android.gms.maps.model.LatLng

data class Beach(
    var id: Int = -1,
    var name: String = "",
    var attractions: MutableList<Int> =  mutableListOf(),
    var activities: MutableList<Int> = mutableListOf(),
    var latLng: LatLng? = null
)