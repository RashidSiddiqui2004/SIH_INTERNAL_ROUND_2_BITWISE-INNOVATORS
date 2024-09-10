package com.example.beachapp.utility

import com.example.beachapp.model.Attraction
import com.example.beachapp.model.Beach
import com.google.android.gms.maps.model.LatLng

object BeachMap {

    // beach id to beach
    val beachIdToBeach = mapOf(
        46 to Beach(
            id = 46,
            name = "Candolim Beach",
            attractions = mutableListOf(1),
            activities = mutableListOf(1, 2, 3, 4),
            latLng = LatLng(15.5180748, 73.7629238) // Swapped to Latitude, Longitude
        ),
        134 to Beach(
            id = 134,
            name = "Puri Beach",
            attractions = mutableListOf(2),
            activities = mutableListOf(5, 6, 7, 8),
            latLng = LatLng(19.7947, 85.8253)
        ),
        131 to Beach(
            id = 131,
            name = "Konark Beach",
            attractions = mutableListOf(3),
            activities = mutableListOf(9, 10, 11, 12),
            latLng = LatLng(19.8833, 86.1)
        ),
        90 to Beach(
            id = 90,
            name = "Om Beach",
            attractions = mutableListOf(4),
            activities = mutableListOf(13, 14, 15, 16),
            latLng = LatLng(14.5190647, 74.3254238)
        ),
        109 to Beach(
            id = 109,
            name = "Kovalam Beach",
            attractions = mutableListOf(5),
            activities = mutableListOf(17, 18, 19, 20),
            latLng = LatLng(8.3950997, 76.9729934)
        ),
        112 to Beach(
            id = 112,
            name = "Varkala Beach",
            attractions = mutableListOf(6),
            activities = mutableListOf(21, 22, 23, 24),
            latLng = LatLng(8.7332285, 76.7054245)
        ),
        84 to Beach(
            id = 84,
            name = "NITK Beach",
            attractions = mutableListOf(7),
            activities = mutableListOf(25, 26, 27, 28),
            latLng = LatLng(13.009928, 74.7940473)
        ),
        6 to Beach(
            id = 6,
            name = "Jalandhar Beach",
            attractions = mutableListOf(8),
            activities = mutableListOf(29, 30, 31, 32),
            latLng = LatLng(20.7090, 70.9851)
        ),
        45 to Beach(
            id = 45,
            name = "Baga Beach",
            attractions = mutableListOf(9),
            activities = mutableListOf(33, 34, 35, 36),
            latLng = LatLng(15.5573721, 73.75098)
        ),
        54 to Beach(
            id = 54,
            name = "Vagator Beach",
            attractions = mutableListOf(10),
            activities = mutableListOf(37, 38, 39, 40),
            latLng = LatLng(15.5976165, 73.733518)
        ),
        49 to Beach(
            id = 49,
            name = "Anjuna Beach",
            attractions = mutableListOf(11),
            activities = mutableListOf(41, 42, 43, 44),
            latLng = LatLng(15.5758504, 73.7402545)
        ),
        156 to Beach(
            id = 156,
            name = "Marina Beach",
            attractions = mutableListOf(12),
            activities = mutableListOf(45, 46, 47, 48),
            latLng = LatLng(13.0532752, 80.2832887)
        ),
        166 to Beach(
            id = 166,
            name = "Kanyakumari Beach",
            attractions = mutableListOf(13),
            activities = mutableListOf(49, 50, 51, 52),
            latLng = LatLng(8.079252, 77.5499338)
        ),
        14 to Beach(
            id = 14,
            name = "Juhu Beach",
            attractions = mutableListOf(14),
            activities = mutableListOf(53, 54, 55, 56),
            latLng = LatLng(19.1026521, 72.8246792)
        ),
        16 to Beach(
            id = 16,
            name = "Versova Beach",
            attractions = mutableListOf(15),
            activities = mutableListOf(57, 58, 59, 60),
            latLng = LatLng(19.1300123, 72.8133251)
        ),
        10 to Beach(
            id = 10,
            name = "Mandvi Beach",
            attractions = mutableListOf(16),
            activities = mutableListOf(61, 62, 63, 64),
            latLng = LatLng(22.8318, 69.3560)
        ),
        175 to Beach(
            id = 175,
            name = "Serenity Beach",
            attractions = mutableListOf(17),
            activities = mutableListOf(65, 66, 67, 68),
            latLng = LatLng(11.9705187, 79.8445562)
        ),
        92 to Beach(
            id = 92,
            name = "Someshwar Beach",
            attractions = mutableListOf(18),
            activities = mutableListOf(69, 70, 71, 72),
            latLng = LatLng(12.7862, 74.8535)
        ),
        161 to Beach(
            id = 161,
            name = "Mahabalipuram Beach",
            attractions = mutableListOf(19),
            activities = mutableListOf(73, 74, 75, 76),
            latLng = LatLng(12.6092262, 80.1956658)
        ),
        96 to Beach(
            id = 96,
            name = "Cherai Beach",
            attractions = mutableListOf(20),
            activities = mutableListOf(77, 78, 79, 80),
            latLng = LatLng(10.1437528, 76.1776012)
        )
    )

    val stateToBeachIds = mapOf(
        "Goa" to listOf(46, 45, 54, 49), // Candolim, Baga, Vagator, Anjuna
        "Odisha" to listOf(134, 131), // Puri, Konark
        "Karnataka" to listOf(90, 84, 92), // Om, NITK, Someshwar
        "Kerala" to listOf(109, 112, 96), // Kovalam, Varkala, Cherai
        "Gujarat" to listOf(6, 10), // Jalandhar, Mandvi
        "Tamil Nadu" to listOf(156, 161, 166), // Marina, Mahabalipuram, Kanyakumari
        "Maharashtra" to listOf(14, 16), // Juhu, Versova
        "Puducherry" to listOf(175) // Serenity
    )

    // beach name to id
    val beachNameToId = mapOf(
        "Candolim Beach" to 46,
        "Puri Beach" to 134,
        "Konark Beach" to 131,
        "Om Beach" to 90,
        "Kovalam Beach" to 109,
        "Varkala Beach" to 112,
        "NITK Beach" to 84,
        "Jalandhar Beach" to 6,
        "Baga Beach" to 45,
        "Vagator Beach" to 54,
        "Anjuna Beach" to 49,
        "Marina Beach" to 156,
        "Kanyakumari Beach" to 166,
        "Juhu Beach" to 14,
        "Versova Beach" to 16,
        "Mandvi Beach" to 10,
        "Serenity Beach" to 175,
        "Someshwar Beach" to 92,
        "Mahabalipuram Beach" to 161,
        "Cherai Beach" to 96
    )

    fun getBeachId(name : String) : Int {
        return beachNameToId.getOrDefault(name, -1)
    }

    fun getBeachName(id : Int) : String {
        return beachIdToBeach.getOrDefault(id, Beach()).name
    }

}