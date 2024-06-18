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
import com.upao.travel_trux.databinding.ActivityEditarBinding
import com.upao.travel_trux.databinding.ActivityMenuBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.User
import java.util.regex.Pattern

class EditarActivity : AppCompatActivity() {

    private val userController = UserController(this)
    private lateinit var binding: ActivityEditarBinding
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getUser = SharedPreferencesManager.getUserData(this)
        if(getUser != null) {
            val user = getUser.split(",")
            val email = user[0]

            userController.getUser(this, email) { user ->
                id = user.id
                binding.etNombre.setText(user.nombre)
                binding.etApellido.setText(user.apellido)
                binding.etCorreo.setText(user.email)
                binding.etTelefono.setText(user.phone)
            }
        }

        val btnGuardar: Button = findViewById(R.id.btnPerfilGuardar)
        btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val correo = binding.etCorreo.text.toString()
            val telefono = binding.etTelefono.text.toString()

            if (!isEmailValid(correo)) {
                binding.etCorreo.error = "Correo electrónico no válido"
                Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isPhoneValid(telefono)) {
                binding.etTelefono.error = "Número de teléfono no válido."
                Toast.makeText(this, "Número de teléfono no válido. Debe tener 9 caracteres y comenzar con un 9.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(nombre.isNotEmpty() && apellido.isNotEmpty() && correo.isNotEmpty() && telefono.isNotEmpty()) {
                val user = User(id, nombre, apellido, correo, telefono)
                userController.updateUser(this, id, user) { isSuccess ->
                    if(isSuccess) {
                        val old_user = SharedPreferencesManager.getUserData(this)
                        if(old_user != null) {
                            val user_global = old_user.split(",")
                            val email = user.email
                            val saveUser = "$email,${user_global[1]}"
                            SharedPreferencesManager.setUserData(this, saveUser)
                        }
                        val intent = Intent(this, MenuActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Error al actualizar datos", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return Pattern.matches(emailPattern, email)
    }

    private fun isPhoneValid(phone: String): Boolean {
        val phonePattern = "^9\\d{8}\$"
        return Pattern.matches(phonePattern, phone)
    }
}