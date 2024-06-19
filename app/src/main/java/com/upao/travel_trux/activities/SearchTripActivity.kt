package com.upao.travel_trux.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.travel_trux.R
import com.upao.travel_trux.adapters.TripAdapter
import com.upao.travel_trux.controllers.TourPackageController
import com.upao.travel_trux.databinding.ActivitySearchTripBinding
import com.upao.travel_trux.models.adaptersModel.TripAdapterModel

class SearchTripActivity : AppCompatActivity() {

    private lateinit var trips: ArrayList<TripAdapterModel>
    private lateinit var tripAdapter: TripAdapter
    private lateinit var binding: ActivitySearchTripBinding
    private val tourPackageController = TourPackageController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT) // light causes internally enforce the navigation bar to be fully transparent
        )
        setContentView(R.layout.activity_search_trip)

        binding = ActivitySearchTripBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trips = ArrayList()
        tripAdapter = TripAdapter(trips)
        addTrips()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = tripAdapter

        tripAdapter.setOnItemClickListener(object : TripAdapter.OnItemClickListener {
            override fun onItemClick(idTrip: Int) {
                val intent = Intent(this@SearchTripActivity, TourActivity::class.java)
                intent.putExtra("idTrip", idTrip)
                startActivity(intent)
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addTrips() {
        tourPackageController.getTourPackages(this) { tourPackages ->
            tourPackages.forEach {
                trips.add(
                    TripAdapterModel(it.id, it.name, it.description, it.image, it.price)
                )
            }
            tripAdapter.notifyDataSetChanged()
        }
    }
}