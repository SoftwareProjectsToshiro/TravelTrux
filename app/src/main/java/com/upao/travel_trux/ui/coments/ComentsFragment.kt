package com.upao.travel_trux.ui.coments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.travel_trux.adapters.ComentsAdapter
import com.upao.travel_trux.databinding.FragmentComentsBinding
import com.upao.travel_trux.models.adaptersModel.ComentsAdapterModel

class ComentsFragment : Fragment() {

    private var _binding: FragmentComentsBinding? = null
    private lateinit var comentsAdapter: ComentsAdapter
    private lateinit var cards: ArrayList<ComentsAdapterModel>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(ComentsViewModel::class.java)

        _binding = FragmentComentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        cards = ArrayList()
        comentsAdapter = ComentsAdapter(cards)
        uploadCards()

        binding.recyclerComents.layoutManager = LinearLayoutManager(context)
        binding.recyclerComents.setHasFixedSize(true)
        binding.recyclerComents.adapter = comentsAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun uploadCards() {
        cards.add(ComentsAdapterModel("Paquete Full Chan-Chan", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore", 3))
        cards.add(ComentsAdapterModel("Paquete Full Chan-Chan", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore", 4))
        cards.add(ComentsAdapterModel("Paquete Full Chan-Chan", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore", 5))
    }
}