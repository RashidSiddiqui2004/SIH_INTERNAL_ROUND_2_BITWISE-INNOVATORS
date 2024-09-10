package com.example.beachapp.model

data class Attraction(
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var location: String = "India",
    var imageUrl: String = "" // Changed from Bitmap to String for storing image URL
) {
    override fun toString(): String {
        return "Attraction: $name - $description (Location: $location)"
    }
}
