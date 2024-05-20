package com.upao.travel_trux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upao.travel_trux.R
import com.upao.travel_trux.models.adaptersModel.UsAdapterModel

class UsAdapter(private val cards:ArrayList<UsAdapterModel>) : RecyclerView.Adapter<UsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.us_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCard = cards[position]
        holder.cardTitle.text = currentCard.title
        holder.cardDescription.text = currentCard.description
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTitle: TextView = itemView.findViewById(R.id.item_us_title)
        val cardDescription: TextView = itemView.findViewById(R.id.item_us_description)
    }
}