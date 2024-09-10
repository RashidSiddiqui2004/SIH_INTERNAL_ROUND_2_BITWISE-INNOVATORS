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
import com.example.beachapp.databinding.FragmentBookmarkedBeachesBinding
import com.example.beachapp.model.Beach
import com.example.beachapp.recyclerViewAdapters.BookmarkedBeachesAdapter
import com.example.beachapp.utility.BeachMap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class BookmarkedBeachesFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkedBeachesBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail: String
    private lateinit var beachList: MutableList<Beach>
    private lateinit var adapter: BookmarkedBeachesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmarked_beaches, container, false)

        initFirebaseServices()
        fetchBeachIds()

        return binding.root
    }

    private fun initFirebaseServices() {
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userEmail = auth.currentUser?.email.toString()

        Log.d("USER_EMAIL", userEmail)
        beachList = mutableListOf()
    }

    private fun setupRecyclerView() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.recyclerView.visibility = View.VISIBLE
        adapter = BookmarkedBeachesAdapter(requireActivity(), beachList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun fetchBeachIds() {
        val userDocRef = firestore.collection("gmail").document(userEmail)
        var count = 0
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val beaches = document.get("beaches") as? List<Int>
                    count = beaches?.size!!
                    beaches?.let {
                        Log.d("DATA_BEACHES", it.toString())
                        fetchBeachData(it)
                    } ?: run {
                        Log.d("ERRORRR", "No beaches found in document")
                    }
                } else {
                    Log.d("ERRORRR", "No such document")
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.txtNoItemInWishList.visibility = View.VISIBLE
                }

                if (count == 0) binding.txtNoItemInWishList.visibility = View.VISIBLE
            }
            .addOnFailureListener { e ->
                Log.e("ERRORRR", "Error fetching document", e)
            }
    }

    private fun fetchBeachData(beachIds: List<Int>) {
        for (id in beachIds) {
            Log.d("BEACH_ID", id.toString())

            BeachMap.beachIdToBeach[id]?.let {
                beachList.add(it)
            } ?: run {
                Log.w("WARNING", "No beach found for ID $id")
            }
        }
        setupRecyclerView()
    }
}
