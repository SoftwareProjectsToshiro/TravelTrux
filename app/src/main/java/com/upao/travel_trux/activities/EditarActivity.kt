package com.upao.travel_trux.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.models.User

class EditarActivity : AppCompatActivity() {

    private val userController = UserController(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContentView(R.layout.activity_editar)

        userController.open()

        val btnGuardar: Button = findViewById(R.id.btnPerfilGuardar)
        btnGuardar.setOnClickListener {
            val id: Long = 0
            val nombre  = findViewById<EditText>(R.id.etNombre).text.toString()
            val email = findViewById<EditText>(R.id.etCorreo).text.toString()
            val phone = findViewById<EditText>(R.id.etTelefono).text.toString()

            val user = User(id, nombre, email, phone)
            userController.update(user)

            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SearchTripActivity::class.java)
            startActivity(intent)
        }
    }
}