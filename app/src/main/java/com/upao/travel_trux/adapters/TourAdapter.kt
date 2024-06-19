package com.upao.travel_trux.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.upao.travel_trux.R
import com.upao.travel_trux.models.responseModel.TourResponse

class TourAdapter(private val tours: ArrayList<TourResponse>) : RecyclerView.Adapter<TourAdapter.ViewHolder>() {

//    private var listener: OnItemClickListener? = null
//
//    interface OnItemClickListener {
//        fun onItemClick(tour: Tour)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        this.listener = listener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tour, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tours[position])
    }

    override fun getItemCount(): Int {
        return tours.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImg: ImageView = itemView.findViewById(R.id.iv_img_tour)
//        private val ivButton: Button = itemView.findViewById(R.id.btn_more_tour_details)
        fun bind(tour: TourResponse) {
            Picasso.get().load(tour.imagen).into(ivImg)
            itemView.findViewById<TextView>(R.id.title_name_tour).text = tour.name
            itemView.findViewById<TextView>(R.id.tv_title_description_tour).text = tour.description
            itemView.findViewById<TextView>(R.id.tv_incluye).text = getIncludesText(tour)
//            ivButton.setOnClickListener {
//                listener?.onItemClick(tour)
//            }
        }

        private fun getIncludesText(tour: TourResponse): String {
            val includes = mutableListOf<String>()
            if (tour.incluyeGuia == 1) includes.add("Guía")
            if (tour.incluyeTransporte == 1) includes.add("Transporte")

            return if (includes.isEmpty()) {
                "No incluye guía ni transporte"
            } else {
                "Incluye: ${includes.joinToString(", ")}"
            }
        }
    }
}