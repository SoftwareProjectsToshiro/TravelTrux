package com.upao.travel_trux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.upao.travel_trux.R
import com.upao.travel_trux.models.adaptersModel.TripAdapterModel

class TripAdapter(private val trips: ArrayList<TripAdapterModel>) : RecyclerView.Adapter<TripAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trips[position])
    }

    override fun getItemCount(): Int {
        return trips.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImg: ImageView = itemView.findViewById(R.id.iv_img)
        fun bind(trip: TripAdapterModel) {
            Picasso.get().load(trip.imagen).into(ivImg)
            itemView.findViewById<TextView>(R.id.title_name).text = trip.nombre
            itemView.findViewById<TextView>(R.id.tv_title_description).text = trip.descripcion
        }
    }
}

