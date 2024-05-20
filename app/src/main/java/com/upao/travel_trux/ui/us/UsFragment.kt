package com.upao.travel_trux.ui.us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.travel_trux.adapters.UsAdapter
import com.upao.travel_trux.databinding.FragmentUsBinding
import com.upao.travel_trux.models.adaptersModel.UsAdapterModel

class UsFragment : Fragment() {

    private var _binding: FragmentUsBinding? = null
    private lateinit var usAdapter: UsAdapter
    private lateinit var cards: ArrayList<UsAdapterModel>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val usViewModel =
            ViewModelProvider(this).get(UsViewModel::class.java)

        _binding = FragmentUsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        cards = ArrayList()
        usAdapter = UsAdapter(cards)
        uploadCards()

        binding.recyclerUs.layoutManager = LinearLayoutManager(context)
        binding.recyclerUs.setHasFixedSize(true)
        binding.recyclerUs.adapter = usAdapter

        return root
    }

    private fun uploadCards() {
        cards.add(UsAdapterModel("Visión", "Brindar un servicio de transporte de carga pesada seguro, eficiente y de calidad, que satisfaga las necesidades de nuestros clientes."))
        cards.add(UsAdapterModel("Misión", "Ser la empresa líder en el transporte de carga pesada en el Perú, reconocida por la calidad de nuestros servicios y la satisfacción de nuestros clientes."))
        cards.add(UsAdapterModel("Objetivo", "Compromiso, responsabilidad, honestidad, respeto, puntualidad, seguridad y calidad."))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}