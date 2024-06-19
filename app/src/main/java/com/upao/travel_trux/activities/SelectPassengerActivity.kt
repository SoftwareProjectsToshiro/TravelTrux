package com.upao.travel_trux.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.databinding.ActivitySelectPassengerBinding

class SelectPassengerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectPassengerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectPassengerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTrip = intent.getIntExtra("idTrip", 0)

        val spinner = binding.spinnerNumbers

        ArrayAdapter.createFromResource(
            this,
            R.array.numbers_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.btnBuyingNext.setOnClickListener {
            val intent = Intent(this, PassengerRegisterActivity::class.java)
            intent.putExtra("idTrip", idTrip)
            intent.putExtra("numPassenger", spinner.selectedItem.toString())
            startActivity(intent)
        }
    }
}