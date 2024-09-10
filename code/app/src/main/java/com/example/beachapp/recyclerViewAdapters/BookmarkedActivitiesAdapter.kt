package com.example.beachapp.recyclerViewAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beachapp.databinding.ActivitiesRecyclerViewItemBinding
import com.example.beachapp.model.Activity

class BookmarkedActivitiesAdapter(
    private val context: Context,
    private var activities: List<Activity>
) : RecyclerView.Adapter<BookmarkedActivitiesAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ActivitiesRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivitiesRecyclerViewItemBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentActivity = activities[position]
        // Bind the activity data to the layout
        holder.binding.activity = currentActivity
        holder.binding.executePendingBindings()  // Ensures immediate data binding updates
    }

    override fun getItemCount(): Int {
        return activities.size
    }

    fun updateList(newList: List<Activity>) {
        activities = newList
        notifyDataSetChanged()
    }
}
