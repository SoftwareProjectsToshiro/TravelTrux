package com.upao.travel_trux.ui.coments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.travel_trux.adapters.ComentsAdapter
import com.upao.travel_trux.controllers.ComentsController
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.databinding.FragmentComentsBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.adaptersModel.ComentsAdapterModel

class ComentsFragment : Fragment() {

    private var _binding: FragmentComentsBinding? = null
    private lateinit var comentsAdapter: ComentsAdapter
    private lateinit var cards: ArrayList<ComentsAdapterModel>
    private lateinit var comentsController: ComentsController
    private lateinit var userController: UserController
    private var id: Int = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentComentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comentsController = ComentsController(requireContext())
        userController = UserController(requireContext())

        cards = ArrayList()
        comentsAdapter = ComentsAdapter(cards)

        val getUser = SharedPreferencesManager.getUserData(requireContext())
        if(getUser != null) {
            val user = getUser.split(",")
            val email = user[0]

            userController.getUser(requireContext(), email) { user ->
                uploadCards(user.id)
            }
        }

        binding.recyclerComents.layoutManager = LinearLayoutManager(context)
        binding.recyclerComents.setHasFixedSize(true)
        binding.recyclerComents.adapter = comentsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun uploadCards(id: Int) {
        comentsController.getComents(requireContext(), id) { coments ->
            coments.forEach {
                cards.add(
                    ComentsAdapterModel(it.id, it.titulo, it.descripcion, it.rating)
                )
            }
            comentsAdapter.notifyDataSetChanged()
        }
    }
}