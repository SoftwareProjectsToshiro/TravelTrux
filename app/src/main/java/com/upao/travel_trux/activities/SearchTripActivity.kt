package com.upao.travel_trux.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.travel_trux.R
import com.upao.travel_trux.adapters.TripAdapter
import com.upao.travel_trux.controllers.TourPackageController
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.databinding.ActivitySearchTripBinding
import com.upao.travel_trux.models.adaptersModel.TripAdapterModel

class SearchTripActivity : AppCompatActivity() {

    private lateinit var tripAdapter: TripAdapter
    private lateinit var trips: ArrayList<TripAdapterModel>
    private lateinit var binding: ActivitySearchTripBinding
    private val tourPackageController = TourPackageController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_trip)

        binding = ActivitySearchTripBinding.inflate(layoutInflater)
        setContentView(binding.root)

        trips = ArrayList()
        tripAdapter = TripAdapter(trips)
        addTrips()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = tripAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addTrips() {

        tourPackageController.getTourPackages(this) { tourPackages ->
            tourPackages.forEach {
                trips.add(
                    TripAdapterModel(it.id, it.name, it.description, it.image)
                )
            }
            tripAdapter.notifyDataSetChanged()
        }
    }
}