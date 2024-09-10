package com.example.beachapp.viewPagerAdapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beachapp.databinding.CarouselImageItemBinding

class CarouselAdapter(var list : List<Bitmap>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {
    class CarouselViewHolder(private val binding : CarouselImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image : Bitmap) {
            binding.imageView.setImageBitmap(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        var binding = CarouselImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(list[position])
    }

}