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
import com.upao.travel_trux.models.User

class RegisterActivity : AppCompatActivity() {
    private val userController = UserController(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        userController.open()

        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            val id: Long = 0
            val nombre = findViewById<EditText>(R.id.etNombre).text.toString()
            val apellido = findViewById<EditText>(R.id.etApellido).text.toString()
            val email = findViewById<EditText>(R.id.etCorreo).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            val phone = findViewById<EditText>(R.id.etTelefono).text.toString()
            val puntos = 0

            val user = User(id, nombre, apellido, email, password, phone, puntos)
            userController.register(user)

            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}