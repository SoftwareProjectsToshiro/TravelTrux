package com.upao.travel_trux.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.travel_trux.R
import com.upao.travel_trux.adapters.TripAdapter
import com.upao.travel_trux.databinding.ActivitySearchTripBinding
import com.upao.travel_trux.models.adaptersModel.TripAdapterModel

class SearchTripActivity : AppCompatActivity() {

    private lateinit var tripAdapter: TripAdapter
    private lateinit var trips: ArrayList<TripAdapterModel>
    private lateinit var binding: ActivitySearchTripBinding

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

    private fun addTrips() {
        trips.add(
            TripAdapterModel(1,"Chan-Chan", "Esta es una antigua ciudad de adobe cerca de Trujillo, fue la capital del reino Chimú antes de ser conquistada por los Incas. Es el sitio arqueológico de adobe más grande de América y ha sido declarado Patrimonio de la Humanidad por la UNESCO, destacándose por su diseño urbano y sus ricas decoraciones en alto relieve",
            R.drawable.chan_chan_img
            )
        )
        trips.add(
            TripAdapterModel(2,"La Esperanza", "Otro distrito de Trujillo, La Esperanza es conocido por ser un área con una población joven y dinámica. Aunque enfrenta desafíos en términos de desarrollo urbano y económico, es un lugar de importante actividad comercial a pequeña y mediana escala.",
            R.drawable.la_esperanza
            )
        )
        trips.add(
            TripAdapterModel(3,"El Porvenir", "Este es un distrito en la provincia de Trujillo conocido por su intensa actividad industrial, especialmente en el sector de la manufactura de calzado. Aunque es más una zona de trabajo y producción, también tiene comunidades vibrantes donde se puede observar la vida diaria de los trujillanos.",
            R.drawable.el_porvenir
            )
        )
    }
}