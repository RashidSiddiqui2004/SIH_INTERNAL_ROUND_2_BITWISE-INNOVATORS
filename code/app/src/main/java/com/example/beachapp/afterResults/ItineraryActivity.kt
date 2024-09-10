package com.example.beachapp.afterResults

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.beachapp.R
import com.example.beachapp.databinding.ActivityItineraryBinding
import com.example.beachapp.utility.ActivityAndAttraction
import com.example.beachapp.utility.BeachMap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItineraryActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var binding: ActivityItineraryBinding
    private var beachId = -1
    private val BEACH_ID = "BeachId"
    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail: String
    private lateinit var bookMarkedActivities: HashSet<Int>

    private lateinit var arr: List<ImageView>
    private lateinit var activityTitles: List<TextView>
    private lateinit var bookmarkImageViews: List<ImageView>
    private var activitiesNumber = mutableListOf(1, 1, 1, 1)
    private var index = 0
    private var activities = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_itinerary)

        arr = listOf(binding.img1, binding.img2, binding.img3, binding.img4)
        activityTitles = listOf(binding.activityTitle1, binding.activityTitle2, binding.activityTitle3, binding.activityTitle4)
        bookmarkImageViews = listOf(binding.imgBookmark1, binding.imgBookmark2, binding.imgBookmark3, binding.imgBookmark4)

        beachId = intent.getIntExtra(BEACH_ID, 46)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        userEmail = auth.currentUser?.email ?: ""

        // Hide main content and show progress bar while loading images
        binding.progressBar.visibility = View.VISIBLE
        binding.linearLayoutMain.visibility = View.INVISIBLE
        binding.imgBack.setOnClickListener { finish() }

        val beachName = BeachMap.getBeachName(beachId)
        activities = BeachMap.beachIdToBeach[beachId]?.activities ?: mutableListOf()

        binding.beachTextForItinerary.text = "Itinerary for $beachName"
        checkBookmarks()

        Log.d("BOOKMARKS", bookMarkedActivities.toString())

        binding.btnSaveItinerary.setOnClickListener {
            saveItinerary()
        }

        setupBookmarkClickListeners()
    }

    private fun setupBookmarkClickListeners() {
        binding.apply {
            listOf(imgBookmark1, imgBookmark2, imgBookmark3, imgBookmark4).forEachIndexed { i, imageView ->
                imageView.setOnClickListener {
                    val activityId = activitiesNumber[i]
                    if (bookMarkedActivities.contains(activityId)) {
                        removeBookmark(activityId)
                        imageView.setImageResource(R.drawable.add_bookmark)
                    } else {
                        addBookmark(activityId)
                        imageView.setImageResource(R.drawable.remove_bookmark)
                    }
                }
            }
        }
    }

    private fun saveItinerary() {
        val docRef = firestore.collection("gmail").document(userEmail)
        docRef.update("beaches", FieldValue.arrayUnion(beachId))
            .addOnSuccessListener {
                Log.d("DATAAAT", "$beachId added successfully")
                Toast.makeText(this@ItineraryActivity, "Beach added to Wishlist", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d("DATAAAT", "$beachId not added successfully")
                Toast.makeText(this@ItineraryActivity, "Can't add to Wishlist", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkBookmarks() {
        val docRef = firestore.collection("gmail").document(userEmail)
        bookMarkedActivities = HashSet()
        docRef.get()
            .addOnSuccessListener {
                val list = it.get("activities") as? List<Int>
                list?.let { activities -> bookMarkedActivities.addAll(activities) }
                Log.d("BOOKMARKS", bookMarkedActivities.toString())
                loadActivities()
            }
            .addOnFailureListener {
                Log.d("BOOKMARKS", "Failed to load bookmarks")
            }

    }

    private fun downloadUrl(storageReference: StorageReference, callback: (Bitmap?) -> Unit) {
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            getBitmapFromFirebaseUrl(this, uri.toString(), callback)
        }.addOnFailureListener { exception ->
            Log.e("FirebaseStorage", "Failed to get download URL", exception)
            callback(null)
        }
    }

    private fun getBitmapFromFirebaseUrl(context: Context, url: String, callback: (Bitmap?) -> Unit) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    callback(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle when the image load is cleared
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Log.e("Glide", "Failed to load image from URL: $url")
                    callback(null)
                }
            })
    }

    private fun removeBookmark(id: Int) {
        val docRef = firestore.collection("gmail").document(userEmail)
        docRef.update("activities", FieldValue.arrayRemove(id))
            .addOnSuccessListener {
                bookMarkedActivities.remove(id)
                Log.d("DATAAAT", "$id removed successfully")
                Toast.makeText(this@ItineraryActivity, "Activity removed from Wishlist", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d("DATAAAT", "$id not removed successfully")
            }
    }

    private fun addBookmark(id: Int) {
        val docRef = firestore.collection("gmail").document(userEmail)
        docRef.update("activities", FieldValue.arrayUnion(id))
            .addOnSuccessListener {
                bookMarkedActivities.add(id)
                Log.d("DATAAAT", "$id added successfully")
                Toast.makeText(this@ItineraryActivity, "Activity added to Wishlist", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d("DATAAAT", "$id not added successfully")
                Toast.makeText(this@ItineraryActivity, "Can't add to Wishlist", Toast.LENGTH_SHORT).show()
            }
    }

    private fun loadActivities() {
                Log.d("BOOKMARKS", bookMarkedActivities.toString()+"kkk")
        activities.let {
            var imagesLoaded = 0
            for (activityId in it) {
                val url = ActivityAndAttraction.BaseStorageUrl + ActivityAndAttraction.activities[activityId]?.imageUrl
                url.let {
                    val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(url)
                    downloadUrl(storageReference) { bitmap ->
                        if (bitmap != null && index < arr.size && index < bookmarkImageViews.size && index < activityTitles.size) {
                            binding.imgBookmark1.setImageResource(if (bookMarkedActivities.contains(activityId)) R.drawable.remove_bookmark else R.drawable.add_bookmark)
                            arr[index].setImageBitmap(bitmap)
                            activitiesNumber[index] = activityId
                            activityTitles[index++].text = ActivityAndAttraction.activities[activityId]?.name
                        }
                        imagesLoaded++
                        if (imagesLoaded == activities.size) {
                            // All images loaded
                            binding.progressBar.visibility = View.GONE
                            binding.linearLayoutMain.visibility = View.VISIBLE
                        }
                    }
                }
            }
        } ?: run {
            binding.progressBar.visibility = View.GONE
            binding.linearLayoutMain.visibility = View.VISIBLE
        }
    }
}
