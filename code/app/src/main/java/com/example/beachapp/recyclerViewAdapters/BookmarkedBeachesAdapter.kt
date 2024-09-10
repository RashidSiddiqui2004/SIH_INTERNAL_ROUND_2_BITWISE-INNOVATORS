package com.example.beachapp.recyclerViewAdapters

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.beachapp.R
import com.example.beachapp.databinding.BeachesRecyclerViewItemBinding
import com.example.beachapp.model.Beach
import com.example.beachapp.utility.ActivityAndAttraction
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class BookmarkedBeachesAdapter(var context : Context, var beaches : List<Beach>) : RecyclerView.Adapter<BookmarkedBeachesAdapter.MyViewHolder>() {

    class MyViewHolder(var binding : BeachesRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root){

        var image = binding.beachImageView
        var name = binding.beachNameTextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var binding = BeachesRecyclerViewItemBinding.inflate(inflater, parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = beaches.get(position).name
        val url = ActivityAndAttraction.BaseStorageUrl + ActivityAndAttraction.attraction.get(beaches.get(position).attractions[0])!!.imageUrl
        val ref = FirebaseStorage.getInstance().getReferenceFromUrl(url)
        downloadUrl(ref) {
            holder.image.setImageBitmap(it)
        }
    }

    override fun getItemCount(): Int {
        return beaches.size
    }

    private fun downloadUrl(storageReference: StorageReference, callback: (Bitmap?) -> Unit) {
        storageReference.downloadUrl.addOnSuccessListener { uri ->
            getBitmapFromFirebaseUrl(context, uri.toString(), callback)
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

    fun updateList(newList: List<Beach>) {
        beaches = newList
        notifyDataSetChanged()
    }
}
