package com.example.beachapp.bookmarkFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beachapp.R
import com.example.beachapp.databinding.FragmentBookmarkedActivitiesBinding
import com.example.beachapp.databinding.FragmentBookmarkedBeachesBinding
import com.example.beachapp.model.Activity
import com.example.beachapp.model.Beach
import com.example.beachapp.recyclerViewAdapters.BookmarkedActivitiesAdapter
import com.example.beachapp.recyclerViewAdapters.BookmarkedBeachesAdapter
import com.example.beachapp.utility.ActivityAndAttraction
import com.example.beachapp.utility.BeachMap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BookmarkedActivitiesFragment : Fragment() {


    private lateinit var binding: FragmentBookmarkedActivitiesBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail: String
    private lateinit var activityList: MutableList<Activity>
    private lateinit var adapter: BookmarkedActivitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmarked_activities, container, false)

        initFirebaseServices()
        fetchActivityIds()

        Log.d("DATAAR", activityList.toString())
        return binding.root
    }

    private fun initFirebaseServices() {
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userEmail = auth.currentUser?.email.toString()

        Log.d("USER_EMAIL", userEmail)
        activityList = mutableListOf()
    }

    private fun setupRecyclerView() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.recyclerView.visibility = View.VISIBLE
        adapter = BookmarkedActivitiesAdapter(requireActivity(), activityList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun fetchActivityIds() {
        val userDocRef = firestore.collection("gmail").document(userEmail)
        var count = 0
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val activities = document.get("activities") as? List<Int>
                    count = activities?.size!!
                    activities?.let {
                        Log.d("DATA_BEACHES", it.toString())
                        fetchBeachDataAndUpdateRecyclerView(it)
                    } ?: run {
                        Log.d("ERRORRR", "No beaches found in document")
                    }
                } else {
                    Log.d("ERRORRR", "No such document")
                    binding.progressBar.visibility = View.INVISIBLE
                }
            if (count == 0) binding.txtNoItemInWishList.visibility = View.VISIBLE
            }
            .addOnFailureListener { e ->
                Log.e("ERRORRR", "Error fetching document", e)
            }
    }

    private fun fetchBeachDataAndUpdateRecyclerView(activityIds: List<Int>) {
        for (id in activityIds) {
            Log.d("BEACH_ID", id.toString())

            ActivityAndAttraction.activities[id]?.let {
                activityList.add(it)
            } ?: run {
                Log.w("WARNING", "No beach found for ID $id")
            }
        }
        setupRecyclerView()
    }
}