package com.upao.travel_trux.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.travel_trux.R
import com.upao.travel_trux.adapters.TripAdapter
import com.upao.travel_trux.databinding.ActivitySearchTripBinding
import com.upao.travel_trux.models.TripModel

class SearchTripActivity : AppCompatActivity() {

    private lateinit var tripAdapter: TripAdapter
    private lateinit var trips: ArrayList<TripModel>
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
        trips.add(TripModel(1,"Chan-Chan", "Esta es una antigua ciudad de adobe cerca de Trujillo, fue la capital del reino Chimú antes de ser conquistada por los Incas. Es el sitio arqueológico de adobe más grande de América y ha sido declarado Patrimonio de la Humanidad por la UNESCO, destacándose por su diseño urbano y sus ricas decoraciones en alto relieve",
            R.drawable.chan_chan_img
        ))
        trips.add(TripModel(2,"La Esperanza", "Otro distrito de Trujillo, La Esperanza es conocido por ser un área con una población joven y dinámica. Aunque enfrenta desafíos en términos de desarrollo urbano y económico, es un lugar de importante actividad comercial a pequeña y mediana escala.",
            R.drawable.la_esperanza
        ))
        trips.add(TripModel(3,"El Porvenir", "Este es un distrito en la provincia de Trujillo conocido por su intensa actividad industrial, especialmente en el sector de la manufactura de calzado. Aunque es más una zona de trabajo y producción, también tiene comunidades vibrantes donde se puede observar la vida diaria de los trujillanos.",
            R.drawable.el_porvenir
        ))
        trips.add(TripModel(4,"Huamacucho", "Situado en la sierra norte de Perú, Huamachuco es una ciudad rica en historia y arqueología. Es conocido por el sitio arqueológico de Marcahuamachuco, a menudo comparado con las ruinas de Machu Picchu por su impresionante arquitectura preinca.",
            R.drawable.huamacucho
        ))
        trips.add(TripModel(5,"Huanchaco", "Famoso por sus caballitos de totora y como un paraíso para los surfistas, Huanchaco es un tradicional pueblo de pescadores en la costa cercana a Trujillo. Es un destino turístico popular por sus playas, su gastronomía basada en mariscos y su ambiente relajado.",
            R.drawable.huanchaco
        ))
        trips.add(TripModel(6,"Trujillo", "Conocida como la \"Ciudad de la Eterna Primavera\", Trujillo es una ciudad vibrante en la costa norte del Perú. Es famosa por su arquitectura colonial bien preservada, su rica historia cultural y su importante rol en la independencia del Perú. Además, es un punto de acceso para explorar antiguas ruinas prehispánicas y disfrutar de festivales tradicionales como el de la Marinera.",
            R.drawable.trujillo
        ))
    }
}