package com.upao.travel_trux.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.databinding.ActivitySelectPassengerBinding

class SelectPassengerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectPassengerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectPassengerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTrip = intent.getIntExtra("idTrip", 0)
    }
}