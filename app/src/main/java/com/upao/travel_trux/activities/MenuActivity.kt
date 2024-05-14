package com.upao.travel_trux.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        val searchTrip: ImageView = findViewById(R.id.iv_img_search)
        searchTrip.setOnClickListener {
            val intent = Intent(this, SearchTripActivity::class.java)
            startActivity(intent)
        }
    }
}