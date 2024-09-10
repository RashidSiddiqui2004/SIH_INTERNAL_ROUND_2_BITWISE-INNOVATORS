package com.example.beachapp.dashboardNavigationFragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.beachapp.R
import com.example.beachapp.databinding.FragmentMapBinding
import com.example.beachapp.utility.BeachForecastData
import com.example.beachapp.utility.BeachMap

import com.example.beachapp.utility.SafeAndUnsafeBeaches
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private val INDIA_BOUNDS = LatLngBounds(
        LatLng(6.7535, 68.1624),  // Southwest corner (near Lakshadweep Islands)
        LatLng(35.5133, 97.3956)  // Northeast corner (including Andaman & Nicobar Islands)
    )

    private lateinit var binding: FragmentMapBinding
    private var googleMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        // Set the listener for marker clicks
        googleMap?.setOnMarkerClickListener(this)

        googleMap?.moveCamera(CameraUpdateFactory.newLatLngBounds(INDIA_BOUNDS, 100))

        // Plot coordinates
        plotCoordinates()
    }

    private fun plotCoordinates() {
        SafeAndUnsafeBeaches.safeBeaches.forEach() {
            it.latLng?.let { it1 ->
                MarkerOptions()
                    .position(it1)
                    .title(it.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            }?.let { it2 ->
                googleMap?.addMarker(
                    it2
                )
            }
        }

        SafeAndUnsafeBeaches.unSafeBeaches.forEach() {
            it.latLng?.let { it1 ->
                MarkerOptions()
                    .position(it1)
                    .title(it.name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }?.let { it2 ->
                googleMap?.addMarker(
                    it2
                )
            }
        }
    }

    // Handle marker clicks
    override fun onMarkerClick(marker: Marker): Boolean {
        marker.title?.let {
            var beachId = BeachMap.getBeachId(it)
            var rcrScore = BeachForecastData.beachIdToForecastData.get(beachId)?.get(0)?.rcr
            if (rcrScore != null) {
                showBeachInfoPopup(beachId, it, rcrScore)
            } else {
                showBeachInfoPopup(beachId, it, 5.0f)
            }
        }
        return true
    }

    fun calculatePercentageDifference(currentValue: Double, averageValue: Double): Double {
        return ((currentValue - averageValue) / averageValue) * 100
    }


    private fun showBeachInfoPopup(beachId: Int, name: String, rcr: Float) {

        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.dialog_custom, null)

        val averageWaveHeight = 1.23
        val averageWavePeriod = 5.5
        val averageTidalElevation = 0.6

        val beachImage = view.findViewById<ImageView>(R.id.beachImage)
        val beachNameText = view.findViewById<TextView>(R.id.beachName)
        val beachDescriptionText = view.findViewById<TextView>(R.id.safetyText)
        val closeButton = view.findViewById<Button>(R.id.closeButton)
        val waveHeightValue = view.findViewById<TextView>(R.id.waveHeightValue)
        val wavePeriodValue = view.findViewById<TextView>(R.id.wavePeriodValue)
        val tidalElevationValue = view.findViewById<TextView>(R.id.tidalElevationValue)
        var beachSafetyIcon = view.findViewById<ImageView>(R.id.safetyIcon)
        val unsafeBeachIcon = ContextCompat.getDrawable(binding.root.context, R.drawable.unsafe_beach)
        val tintColor = ContextCompat.getColor(binding.root.context, R.color.unsafe)
        val waveHeightIcon = view.findViewById<ImageView>(R.id.waveHeightIcon)
        val wavePeriodIcon = view.findViewById<ImageView>(R.id.wavePeriodIcon)
        val tidalElevationIcon = view.findViewById<ImageView>(R.id.tidalElevationIcon)

        beachNameText.text = name

//        check via api stored data, whether safe or not
//        according to rule-bases heuristics, as specified

        Log.d("RCR", name + rcr)
        if (rcr < 0.45) {
            beachDescriptionText.setTextColor(Color.GREEN)
            beachDescriptionText.text = "$name is safe to visit."
        } else {
            beachDescriptionText.setTextColor(Color.RED)
            beachDescriptionText.text = "$name is not safe to visit."
            beachSafetyIcon.setImageDrawable(unsafeBeachIcon)
            beachSafetyIcon.setColorFilter(tintColor)
        }

        beachImage.setImageResource(R.drawable.beach_photo)  // Update with the beach image


//        update the 3 parameters waveHeightValue, wavePeriodValue, tidalElevationValue as per formula

        var data = BeachForecastData.beachIdToForecastData.get(beachId)?.get(0)

        var currentTidalElevation = averageTidalElevation
        var currentWaveHeight = averageWaveHeight
        var currentWavePeriod = averageWavePeriod

        if (data != null) {
            currentTidalElevation = data.tidal_elevation.toDouble()
            currentWaveHeight =data.wave_height.toDouble()
            currentWavePeriod = data.wave_period.toDouble()
        }


        val waveHeightDifference = calculatePercentageDifference(currentWaveHeight, averageWaveHeight)

        val wavePeriodDifference = calculatePercentageDifference(currentWavePeriod, averageWavePeriod)

        val tidalElevationDifference = calculatePercentageDifference(currentTidalElevation, averageTidalElevation)

        val isWaveHeightIncreasing = ((waveHeightDifference.toInt()) > 0)
        val isWavePeriodIncreasing = ((wavePeriodDifference.toInt()) > 0)
        val isTidalElevationIncreasing = ((tidalElevationDifference.toInt()) > 0)

        val increasingColor = ContextCompat.getColor(requireContext(), R.color.increasing)
        val decreasingColor = ContextCompat.getColor(requireContext(), R.color.decreasing)

        waveHeightValue.text = waveHeightDifference.toInt().toString() + "%"
        wavePeriodValue.text = wavePeriodDifference.toInt().toString() + "%"
        tidalElevationValue.text = tidalElevationDifference.toInt().toString() + "%"

        if(isWaveHeightIncreasing){
            waveHeightIcon.setImageResource(R.drawable.increasing_parameter)
            waveHeightIcon.setColorFilter(increasingColor)
        }
        else{
            waveHeightIcon.setImageResource(R.drawable.decreasing_paramter)
            waveHeightIcon.setColorFilter(decreasingColor)
        }
        if(isWavePeriodIncreasing){
            wavePeriodIcon.setImageResource(R.drawable.increasing_parameter)
            wavePeriodIcon.setColorFilter(increasingColor)
        }
        else{
            wavePeriodIcon.setImageResource(R.drawable.decreasing_paramter)
            wavePeriodIcon.setColorFilter(decreasingColor)

        }
        if(isTidalElevationIncreasing){
            tidalElevationIcon.setImageResource(R.drawable.increasing_parameter)
            tidalElevationIcon.setColorFilter(increasingColor)
        }
        else{
            tidalElevationIcon.setImageResource(R.drawable.decreasing_paramter)
            tidalElevationIcon.setColorFilter(decreasingColor)
        }


        // Create and show the custom AlertDialog
        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setView(view)  // Set the custom view
            .create()

        // Close the dialog when the button is clicked
        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

}

