package com.upao.travel_trux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upao.travel_trux.R
import com.upao.travel_trux.models.adaptersModel.ComentsAdapterModel

class ComentsAdapter(private val coments: ArrayList<ComentsAdapterModel>) : RecyclerView.Adapter<ComentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coments, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coments[position])
    }

    override fun getItemCount(): Int {
        return coments.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comentsAdapterModel: ComentsAdapterModel) {
            itemView.findViewById<TextView>(R.id.coment_title).text = comentsAdapterModel.title
            itemView.findViewById<TextView>(R.id.coment_description).text = comentsAdapterModel.description
            itemView.findViewById<RatingBar>(R.id.coment_rating).rating = comentsAdapterModel.rating.toFloat()
        }
    }
}