package com.upao.travel_trux.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.upao.travel_trux.activities.EditarActivity
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.databinding.FragmentProfileBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val button: Button = binding.btnEditarPerfil

       button.setOnClickListener{
           val intent = Intent(activity, EditarActivity::class.java)
           startActivity(intent)
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}