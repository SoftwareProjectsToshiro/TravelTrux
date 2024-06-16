package com.upao.travel_trux.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.databinding.ActivityMenuBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.requestModel.LoginRequest

class MenuActivity : AppCompatActivity() {
    private val userController = UserController(this)
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchTrip: ImageView = findViewById(R.id.iv_img_search)
        searchTrip.setOnClickListener {
            val intent = Intent(this, SearchTripActivity::class.java)
            startActivity(intent)
        }

        val profile: ImageView = findViewById(R.id.iv_img_profile)
        profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}