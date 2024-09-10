package com.example.beachapp.dashboardNavigationFragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.beachapp.R
import com.example.beachapp.ResultActivity
import com.example.beachapp.databinding.FragmentHomeBinding
import com.example.beachapp.model.ForecastData
import com.example.beachapp.repository.BeachRepository
import com.example.beachapp.utility.BeachForecastData
import com.example.beachapp.utility.BeachMap
import com.example.beachapp.viewPagerAdapters.CarouselAdapter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore

    private lateinit var binding: FragmentHomeBinding

    private lateinit var auth : FirebaseAuth

    private lateinit var userEmail : String

    private var BEACH_ID = "BeachId"

    // Mapping of place names to IDs
    private val placeIdMap = BeachMap.beachNameToId

    private lateinit var repository: BeachRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.msg.visibility = View.INVISIBLE

        auth = FirebaseAuth.getInstance()
        userEmail = auth.currentUser?.email.toString()
        firestore = FirebaseFirestore.getInstance()

        showNameIfExists()

        val images = listOf(
            getBitmap(R.drawable.mamallapuram_beach),
            getBitmap(R.drawable.beach_photo),
            getBitmap(R.drawable.kovalam_beach)
        )

        someFunction()
//        var data = BeachForecastData.getForABeach(14)
//
//        Log.d("got_data", data.toString())
//
//        plotRCRData(data)

        val adapter = CarouselAdapter(images)
        binding.viewPagerCarousel.adapter = adapter
        binding.viewPagerCarousel.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val places = placeIdMap.keys.toList() // Get the list of place names

        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, places)
        binding.autoCompleteSearch.setAdapter(adapter2)
        binding.autoCompleteSearch.threshold = 1

        binding.autoCompleteSearch.setOnItemClickListener { parent, view, position, id ->
            val selectedBeach = parent.getItemAtPosition(position).toString() // Get the selected beach name
            val beachId = placeIdMap[selectedBeach] // Get the corresponding ID from the map
            Log.d("POSITION", "Selected Beach: $selectedBeach, ID: $beachId")

            if (beachId != null) {
                apiCall(beachId)
            }
        }


        return binding.root
    }

    private fun apiCall(beachId : Int) {
        var intent = Intent(requireActivity(), ResultActivity::class.java)
        intent.putExtra(BEACH_ID, beachId)
        startActivity(intent)
    }

    private fun fetchBeachForecasts(beachId: Int) {
        repository.fetchClimaticConditions(beachId)
    }

    private fun getBitmap(image: Int): Bitmap {
        return BitmapFactory.decodeResource(resources, image)
    }

    private fun showNameIfExists() {
        val docRef = firestore.collection("gmail").document(userEmail)
        docRef.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val document = it.result
                if (document.contains("name")) {
                    binding.txtGreet.text = "Hello, " + document.get("name").toString()
                }
            }
        }
    }

    private fun plotRCRData(data: MutableList<ForecastData>) {
        binding.lineChart.resetZoom()
        plotLineChart(
            data,
            binding.lineChart,
            "Rip Current Risk",
            { data -> data.rcr }, // Assuming rcr is the field for Rip Current Risk
            "Rip Current Risk Over Time"
        )
    }

    private fun plotLineChart(
        list :  MutableList<ForecastData>,
        lineChart: LineChart,
        label: String,
        valueExtractor: (ForecastData) -> Float, // Lambda to extract the value to plot
        descriptionText: String
    ) {
        val entries = ArrayList<Entry>()
        val xAxisLabels = ArrayList<String>()

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
                color = ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark)
                lineWidth = 2f
                valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.black)
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
//                description.text = descriptionText
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

    fun someFunction() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val data = BeachForecastData.getForABeach(14)
                plotRCRData(data)
                binding.msg.visibility = View.VISIBLE
                binding.msg.setTextColor(resources.getColor(R.color.unsafe))
            } catch (e: Exception) {
                Log.e("API Error", "Exception occurred: ${e.message}", e)
            }
        }
    }

}
