package com.example.beachapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.beachapp.afterResults.ItineraryActivity
import com.example.beachapp.databinding.ActivityResultBinding
import com.example.beachapp.model.ForecastData
import com.example.beachapp.utility.ActivityAndAttraction
import com.example.beachapp.utility.BeachForecastData
import com.example.beachapp.utility.BeachMap
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Locale

class ResultActivity : AppCompatActivity() {

    private val BEACH_ID = "BeachId"
    private lateinit var binding: ActivityResultBinding
    private var beachId = -1 // Default invalid value
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        beachId = intent.getIntExtra(BEACH_ID, -1)
        Log.d("HELLO", BeachForecastData.beachIdToForecastData.toString())

        initFirebaseServices()

        binding.beachImage.visibility = View.INVISIBLE

        binding.imgBack.setOnClickListener { finish() }

        binding.btnViewItinerary.setOnClickListener {
            var intent = Intent(this@ResultActivity, ItineraryActivity::class.java)
            intent.putExtra(BEACH_ID, beachId)
            startActivity(intent)
        }

        binding.btnAddToWishlist.setOnClickListener {
            bookmarkThisBeach()
        }

        val url = ActivityAndAttraction.BaseStorageUrl + (ActivityAndAttraction.attraction.get(BeachMap.beachIdToBeach.get(beachId)?.attractions?.get(0))?.imageUrl)

        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(url)

        // Fetch the download URL
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            // Call the function with the HTTP URL obtained
            getBitmapFromFirebaseUrl(this, uri.toString()) { bitmap ->
                if (bitmap != null) {
                    // Set the Bitmap to the ImageView
                    binding.beachImage.setImageBitmap(bitmap)
                    binding.beachImage.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.INVISIBLE
                } else {
                    Log.e("BitmapLoader", "Failed to load Bitmap from the URL.")
                }
            }
        }.addOnFailureListener { exception ->
            Log.e("FirebaseStorage", "Failed to get download URL", exception)
        }

        showBeachInfo(beachId)

        // Verify if data exists before plotting
        if (BeachForecastData.beachIdToForecastData.containsKey(beachId)) {
            plotTidalData() // Call the correct method to plot the actual data
            plotRCRData()
            plotWaveHeightData()
            plotWavePeriodData()
        } else {
            Log.e("ResultActivity", "No data available for beachId: $beachId")
        }
    }

    private fun bookmarkThisBeach() {
        val docRef = firestore.collection("gmail").document(userEmail)
//                Log.d("DATAAAT", "$beachId added successfully")

        docRef.update("beaches", FieldValue.arrayUnion(beachId))
            .addOnSuccessListener {
                Log.d("DATAAAT", "$beachId added successfully")
                Toast.makeText(this@ResultActivity, "Beach added to Wishlist", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d("DATAAAT", "$beachId not added successfully")
                Toast.makeText(this@ResultActivity, "Can't add to Wishlist", Toast.LENGTH_SHORT).show()
            }
    }

    private fun getBitmapFromFirebaseUrl(context: Context, url: String, callback: (Bitmap?) -> Unit) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: Caching strategy
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    callback(resource) // Bitmap successfully loaded
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle when the image load is cleared
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Log.e("Glide", "Failed to load image from URL: $url")
                    callback(null) // Notify that loading failed
                }
            })
    }

    private fun plotTidalData() {
        plotLineChart(
            binding.lineChartTidalHeight,
            "Tidal Elevation",
            { data -> data.tidal_elevation },
            "Tidal Elevation Over Time"
        )
    }

    private fun plotWaveHeightData() {
        plotLineChart(
            binding.lineChartWaveHeight,
            "Wave Height",
            { data -> data.wave_height },
            "Wave Height Over Time"
        )
    }

    private fun plotWavePeriodData() {
        plotLineChart(
            binding.lineChartWavePeriod,
            "Wave Period",
            { data -> data.wave_period },
            "Wave Period Over Time"
        )
    }

    private fun plotRCRData() {
        plotLineChart(
            binding.lineChartRcr,
            "Rip Current Risk",
            { data -> data.rcr }, // Assuming rcr is the field for Rip Current Risk
            "Rip Current Risk Over Time"
        )
    }

    // Generic function to plot data on a LineChart
    private fun plotLineChart(
        lineChart: LineChart,
        label: String,
        valueExtractor: (ForecastData) -> Float, // Lambda to extract the value to plot
        descriptionText: String
    ) {
        val entries = ArrayList<Entry>()
        val xAxisLabels = ArrayList<String>()

        // Fetch the list of forecast data for the beachId
        val list = BeachForecastData.beachIdToForecastData[beachId]
        if (list != null && list.isNotEmpty()) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val displayFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())

            // Determine the start index to plot the last 8 data points
            val startIndex = if (list.size > 7) list.size - 7 else 0

            for (index in startIndex until list.size) {
                val data = list[index]
                try {
                    val date = dateFormat.parse("${data.forecast_date} ${data.forecast_time}")
                    val value = valueExtractor(data) // Extract the desired value for the chart

                    if (date != null) {
                        entries.add(Entry((index - startIndex).toFloat(), value))
                        xAxisLabels.add(displayFormat.format(date))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


            val dataSet = LineDataSet(entries, "").apply {
                color = ContextCompat.getColor(this@ResultActivity, android.R.color.holo_blue_dark)
                lineWidth = 2f
                valueTextColor = ContextCompat.getColor(this@ResultActivity, android.R.color.black)
                setDrawValues(false)
            }

            // Set up the line data and chart configurations
            val lineData = LineData(dataSet)

            lineChart.apply {
                data = lineData
                xAxis.apply {
                    labelRotationAngle = -50f
                    position = XAxis.XAxisPosition.BOTTOM
                    granularity = 1f // Ensure the labels are displayed correctly
                    valueFormatter = object : ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            return if (value.toInt() < xAxisLabels.size) xAxisLabels[value.toInt()] else ""
                        }
                    }
                    setDrawGridLines(false)
                }
                axisRight.isEnabled = false
                description.isEnabled = false
                setTouchEnabled(true)
                setPinchZoom(true)
                invalidate() // Refresh the chart to display data
            }
        } else {
            // Handle the case where list is null or empty
            Log.e("ResultActivity", "No data available for plotting.")
            lineChart.visibility = View.GONE
        }
    }

    private fun showBeachInfo(beachId: Int) {
        val averageWaveHeight = 1.23
        val averageWavePeriod = 5.5
        val averageTidalElevation = 0.6

        val name = BeachMap.getBeachName(beachId)
        val rcr = BeachForecastData.beachIdToForecastData.get(beachId)?.get(0)?.rcr
        val beachImage = binding.beachImage
        val beachNameText = binding.beachName
        var beachSafetyIcon = binding.safetyIcon
        val beachDescriptionText = binding.safetyText
        val waveHeightValue = binding.waveHeightValue
        val wavePeriodValue = binding.wavePeriodValue
        val tidalElevationValue = binding.tidalElevationValue
        val unsafeBeachIcon = ContextCompat.getDrawable(binding.root.context, R.drawable.unsafe_beach)
        val tintColor = ContextCompat.getColor(binding.root.context, R.color.unsafe)

        val waveHeightIcon = binding.waveHeightIcon
        val wavePeriodIcon = binding.wavePeriodIcon
        val tidalElevationIcon = binding.tidalElevationIcon
        beachNameText.text = name

//        check via api stored data, whether safe or not
//        according to rule-bases heuristics

        Log.d("RCR", name + rcr)
        if (rcr != null) {
            if (rcr < 0.45) {
                beachDescriptionText.setTextColor(Color.GREEN)
                beachDescriptionText.text = "$name is safe to visit."
            } else {
                beachDescriptionText.setTextColor(Color.RED)
                beachSafetyIcon.setImageDrawable(unsafeBeachIcon)
                beachSafetyIcon.setColorFilter(tintColor)
                beachDescriptionText.text = "$name is not safe to visit."
            }
        }
//        update the 3 parameters waveHeightValue, wavePeriodValue, tidalElevationValue as given formula

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

        val increasingColor = ContextCompat.getColor(binding.root.context, R.color.increasing)
        val decreasingColor = ContextCompat.getColor(binding.root.context, R.color.decreasing)

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

    }


    fun calculatePercentageDifference(currentValue: Double, averageValue: Double): Double {
        return ((currentValue - averageValue) / averageValue) * 100
    }

    private fun initFirebaseServices() {
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userEmail = auth.currentUser?.email.toString()
    }


}
