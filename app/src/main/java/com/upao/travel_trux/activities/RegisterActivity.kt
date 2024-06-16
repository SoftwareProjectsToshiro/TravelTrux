package com.upao.travel_trux.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.databinding.ActivityRegisterBinding
import com.upao.travel_trux.models.User
import com.upao.travel_trux.models.requestModel.RegisterRequest

class RegisterActivity : AppCompatActivity() {
    private val userController = UserController(this)
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener {

            val nombre = findViewById<EditText>(R.id.etNombre).text.toString()
            val apellido = findViewById<EditText>(R.id.etApellido).text.toString()
            val email = findViewById<EditText>(R.id.etCorreo).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            val phone = findViewById<EditText>(R.id.etTelefono).text.toString()
            val user = RegisterRequest(nombre, apellido, email, password, phone)
            userController.register(this, user) { isSuccess ->
                if (isSuccess) {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}