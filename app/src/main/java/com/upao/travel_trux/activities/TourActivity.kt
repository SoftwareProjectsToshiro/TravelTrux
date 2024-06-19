package com.upao.travel_trux.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upao.travel_trux.R
import com.upao.travel_trux.adapters.TourAdapter
import com.upao.travel_trux.controllers.TourController
import com.upao.travel_trux.databinding.ActivityTourBinding
import com.upao.travel_trux.models.responseModel.TourResponse

class TourActivity : AppCompatActivity() {

    private lateinit var toursList: ArrayList<TourResponse>
    private lateinit var tourAdapter: TourAdapter
    private lateinit var binding: ActivityTourBinding
    private val tourController = TourController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        binding = ActivityTourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTrip = intent.getIntExtra("idTrip", 0)

        toursList = ArrayList()
        tourAdapter = TourAdapter(toursList)
        addTours(idTrip)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_tour)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = tourAdapter

        binding.btnCompraAhora.setOnClickListener {
            val intent = Intent(this, SelectPassengerActivity::class.java)
            intent.putExtra("idTrip", idTrip)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addTours(idTrip: Int) {
        tourController.getTours(this, idTrip) { tours ->
            tours.forEach {
                toursList.add(
                    TourResponse(it.id, it.tourPackageId, it.name,
                        it.description, it.imagen, it.incluyeGuia,
                        it.incluyeTransporte, it.hora_inicio, it.hora_fin,
                        it.isActived, it.isDeleted, it.created_at,
                        it.updated_at)
                )
            }
            tourAdapter.notifyDataSetChanged()
        }
    }
}