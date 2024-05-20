package com.upao.travel_trux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upao.travel_trux.R
import com.upao.travel_trux.models.adaptersModel.TripAdapterModel

class TripAdapter(private val trips: ArrayList<TripAdapterModel>) : RecyclerView.Adapter<TripAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trip_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTrip = trips[position]
        holder.tripImage.setImageResource(currentTrip.image)
        holder.tripTitle.text = currentTrip.title
        holder.tripDescription.text = currentTrip.description
    }

    override fun getItemCount(): Int {
        return trips.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tripImage: ImageView = itemView.findViewById(R.id.iv_img)
        val tripTitle: TextView = itemView.findViewById(R.id.title_name)
        val tripDescription: TextView = itemView.findViewById(R.id.tv_title_description)
    }
}

