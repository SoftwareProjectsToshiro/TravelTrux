package com.upao.travel_trux.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.controllers.TouristController
import com.upao.travel_trux.databinding.ActivityPassengerRegisterBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.Tourist

class PassengerRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPassengerRegisterBinding
    private val touristController = TouristController(this)
    private var userEmail = ""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPassengerRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idTrip = intent.getIntExtra("idTrip", 0)
        val numPassenger = intent.getStringExtra("numPassenger")
        var numPassangerCurrent = 1

        val getUser = SharedPreferencesManager.getUserData(this)
        val user = getUser?.split(",")
        if(user != null) {
            userEmail = user[0]
        }

        binding.tvRegistroPasajeroNumero.text = "Registro de pasajero #${numPassangerCurrent}"

        val spinnerTypeDocument = binding.spinnerTipoDocumento
        val spinnerGender = binding.spinnerSexo

        ArrayAdapter.createFromResource(
            this,
            R.array.document_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTypeDocument.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.sex,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGender.adapter = adapter
        }

        binding.btnSiguiente.setOnClickListener {
            val dni = binding.etNumeroDocumento.text.toString()
            val name = binding.etNombres.text.toString()
            val patherLastName = binding.etApellidoPaterno.text.toString()
            val motherLastName = binding.etApellidoMaterno.text.toString()
            val sex = spinnerGender.selectedItem.toString()
//            val typeDocument = spinnerTypeDocument.selectedItem.toString()
            val birthdate = binding.etFecha.text.toString()
            val phone = binding.etTelefono.text.toString()
            val email = binding.etEmail.text.toString()
            val tourist = Tourist(userEmail, dni, name,
                patherLastName, motherLastName, sex,
                birthdate, phone, email)

            touristController.registerTourist(this, tourist) { isSuccess ->
                if (isSuccess) {
                    cleanFields()
                    if (numPassenger != null) {
                        if (numPassenger.toInt() > numPassangerCurrent) {
                            numPassangerCurrent = numPassangerCurrent.plus(1)
                            binding.tvRegistroPasajeroNumero.text = "Registro de pasajero #${numPassangerCurrent}"
                            println("numPassangerCurrent: $numPassangerCurrent")
                        } else {
                            val i = Intent(this, NiubizActivity::class.java)
                            i.putExtra("idTrip", idTrip)
                            startActivity(i)
                            finish()
                        }
                    }
                }
            }
        }

    }

    private fun cleanFields() {
        binding.etNumeroDocumento.text.clear()
        binding.etNombres.text.clear()
        binding.etApellidoPaterno.text.clear()
        binding.etApellidoMaterno.text.clear()
        binding.etFecha.text.clear()
        binding.etTelefono.text.clear()
        binding.etEmail.text.clear()
    }
}