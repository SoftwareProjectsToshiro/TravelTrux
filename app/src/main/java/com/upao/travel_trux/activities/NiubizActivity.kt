package com.upao.travel_trux.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.controllers.ReservationController
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.databinding.ActivityNiubizBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.requestModel.ReservationRequest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NiubizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNiubizBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        binding = ActivityNiubizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idPedido = intent.getIntExtra("idTrip",0)
        val userId = intent.getIntExtra("userId",0)
        val reservationId = intent.getStringExtra("reservation_id")

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

        binding.webview.loadUrl("https://api-trux-travel.strategyec.com/public_html/niubiz/$reservationId")

        binding.webview.webViewClient = object : WebViewClient() {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if(url == "https://api-trux-travel.strategyec.com/public_html/payment-success") {
                    val intent = Intent(this@NiubizActivity, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}