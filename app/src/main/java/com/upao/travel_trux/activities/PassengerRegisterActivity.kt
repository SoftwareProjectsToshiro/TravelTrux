package com.upao.travel_trux.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.controllers.TouristController
import com.upao.travel_trux.databinding.ActivityPassengerRegisterBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.requestModel.TouristRequest
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class PassengerRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPassengerRegisterBinding
    private lateinit var selectedDateTextView: TextView
    private lateinit var selectDateButton: Button
    private val touristController = TouristController(this)
    private var userEmail = ""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPassengerRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedDateTextView = findViewById(R.id.selectedDateTextView)
        selectDateButton = findViewById(R.id.et_fecha_salida)

        selectDateButton.setOnClickListener {
            showDatePickerDialog()
        }

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

        binding.etNumeroDocumento.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No se necesita implementar
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.length >= 8) {
                    llamarControlador()
                }
            }
        })

        binding.btnSiguiente.setOnClickListener {
            val numDocument = binding.etNumeroDocumento.text.toString()
            val name = binding.etNombres.text.toString()
            val patherLastName = binding.etApellidoPaterno.text.toString()
            val motherLastName = binding.etApellidoMaterno.text.toString()
            val sex = spinnerGender.selectedItem.toString()
            val typeDocument = spinnerTypeDocument.selectedItem.toString()
            val dayOut = selectedDateTextView.text.toString()
            val birthdate = binding.etFechaNacimiento.text.toString()
            val phone = binding.etTelefono.text.toString()
            val email = binding.etEmail.text.toString()
            val touristRequest = TouristRequest(idTrip, userEmail, numDocument, name,
                patherLastName, motherLastName, sex, typeDocument,
                dayOut, birthdate, phone, email)

            touristController.registerTourist(this, touristRequest) { isSuccess ->
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
        binding.selectedDateTextView.text = R.string.fecha.toString()
        binding.etFechaNacimiento.text.clear()
        binding.etTelefono.text.clear()
        binding.etEmail.text.clear()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun llamarControlador() {
        touristController.getTourist(this, binding.etNumeroDocumento.text.toString()) { tourist ->
            if(tourist.email != "") {
                binding.etNombres.setText(tourist.nombre)
                binding.etApellidoPaterno.setText(tourist.apellidoPaterno)
                binding.etApellidoMaterno.setText(tourist.apellidoMaterno)
                if(tourist.sexo == "Masculino") {
                    binding.spinnerSexo.setSelection(0)
                } else {
                    binding.spinnerSexo.setSelection(1)
                }
                when(tourist.tipoDocumento) {
                    "DNI" -> binding.spinnerTipoDocumento.setSelection(0)
                    "Pasaporte" -> binding.spinnerTipoDocumento.setSelection(1)
                    "Carnet de Extranjeria" -> binding.spinnerTipoDocumento.setSelection(2)
                }
                binding.etFechaNacimiento.setText(calculateDateDifference(tourist.fechaNacimiento))
                binding.etTelefono.setText(tourist.telefono)
                binding.etEmail.setText(tourist.email)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateDateDifference(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDate = LocalDate.parse(dateString, formatter)
        val currentDate = LocalDate.now()
        val period = Period.between(startDate, currentDate)

        return "${period.years}"
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(selectedYear, selectedMonth, selectedDay)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateString = dateFormat.format(selectedDate.time)

            selectedDateTextView.text = dateString
        }, year, month, day)

        datePickerDialog.datePicker.minDate = calendar.timeInMillis

        datePickerDialog.show()
    }
}