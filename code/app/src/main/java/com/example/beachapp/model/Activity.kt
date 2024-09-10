package com.example.beachapp.model

import android.graphics.Bitmap

data class Activity(
    var id: Int,
    var name: String,
    var description: String,
    var timing: String,
    var imageUrl: String = "" // Changed from Bitmap to String for storing image URL
) {

    override fun toString(): String {
        return "Activity: $name - $description (Best Time: $timing)"
    }
}