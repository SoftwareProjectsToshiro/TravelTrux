package com.upao.travel_trux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.upao.travel_trux.R
import com.upao.travel_trux.models.adaptersModel.TripAdapterModel

class TripAdapter(private val trips: ArrayList<TripAdapterModel>) : RecyclerView.Adapter<TripAdapter.ViewHolder>(){

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(idTrip: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_package_tour, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listener?.let { holder.bind(trips[position], it) }
    }

    override fun getItemCount(): Int {
        return trips.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImg: ImageView = itemView.findViewById(R.id.iv_img)
        private val ivButton: Button = itemView.findViewById(R.id.btn_more_trip_details)
        fun bind(trip: TripAdapterModel, listener: OnItemClickListener) {
            Picasso.get().load(trip.imagen).into(ivImg)
            itemView.findViewById<TextView>(R.id.title_name).text = trip.nombre
            itemView.findViewById<TextView>(R.id.tv_title_description).text = trip.descripcion
            itemView.findViewById<TextView>(R.id.tv_amount).text = trip.precio.toString()
            ivButton.setOnClickListener {
                listener.onItemClick(trip.id)
            }
        }
    }
}

