package com.upao.travel_trux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.upao.travel_trux.R
import com.upao.travel_trux.models.adaptersModel.ComentsAdapterModel

class ComentsAdapter(
    private val coments: ArrayList<ComentsAdapterModel>,
    private val onButtonClicked: (ComentsAdapterModel) -> Unit) : RecyclerView.Adapter<ComentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coments, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = coments[position]

        if (item.content.isEmpty()) {
            holder.comentEditText.visibility = View.VISIBLE
            holder.comentDescription.visibility = View.GONE
            holder.comentTitle.visibility = View.VISIBLE
        } else {
            holder.comentEditText.visibility = View.GONE
            holder.comentTitle.visibility = View.VISIBLE
            holder.comentTitle.text = item.content
        }

        holder.comentDescription.text = item.content
        holder.comentRating.rating = item.rating.toFloat()

        holder.comentRating.setOnRatingBarChangeListener { _, rating, _ ->
            item.rating = rating.toInt()
        }
        holder.comentButton.setOnClickListener {
            onButtonClicked(item)
        }

    }

    override fun getItemCount(): Int {
        return coments.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comentTitle: TextView = itemView.findViewById(R.id.coment_title)
        val comentEditText: EditText = itemView.findViewById(R.id.coment_no_coment)
        val comentDescription: TextView = itemView.findViewById(R.id.coment_description)
        val comentRating: RatingBar = itemView.findViewById(R.id.coment_rating)
        val comentButton: Button = itemView.findViewById(R.id.coment_button)
    }
}