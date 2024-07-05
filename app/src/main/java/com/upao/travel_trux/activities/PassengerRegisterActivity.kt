package com.upao.travel_trux.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.upao.travel_trux.R
import com.upao.travel_trux.controllers.ReservationController
import com.upao.travel_trux.controllers.TouristController
import com.upao.travel_trux.controllers.UserController
import com.upao.travel_trux.databinding.ActivityPassengerRegisterBinding
import com.upao.travel_trux.helpers.SharedPreferencesManager
import com.upao.travel_trux.models.requestModel.ReservationRequest
import com.upao.travel_trux.models.requestModel.TouristRequest
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar
import java.util.Locale
import java.util.regex.Pattern

class PassengerRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPassengerRegisterBinding
    private lateinit var selectedDateTextView: TextView
    private lateinit var selectedDateBirthDayTextView: TextView
    private lateinit var edadTextView: TextView
    private lateinit var selectDateButton: Button
    private lateinit var selectDateBirthDayButton: Button
    private lateinit var etFechaNacimiento: TextView
    private val touristController = TouristController(this)
    private val userController = UserController(this)
    private val reservationController = ReservationController(this)
    private var userEmail = ""
    private var dateBirth = ""
    private var userId: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPassengerRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedDateTextView = findViewById(R.id.selectedDateTextView)
        selectDateButton = findViewById(R.id.et_fecha_salida)

        selectedDateBirthDayTextView = findViewById(R.id.selectedDateBirthDayTextView)
        selectDateBirthDayButton = findViewById(R.id.btn_fecha_nacimiento)
        etFechaNacimiento = findViewById(R.id.et_fecha_nacimiento)
        edadTextView = findViewById(R.id.edadTextView)

        etFechaNacimiento.visibility = View.GONE
        edadTextView.visibility = View.GONE

        selectDateButton.setOnClickListener {
            showDatePickerDialog()
        }

        selectDateBirthDayButton.setOnClickListener {
            showPastDatePickerDialog()
        }

        val idTrip = intent.getIntExtra("idTrip", 0)
        val numPassenger = intent.getStringExtra("numPassenger")
        var numPassangerCurrent = 1

        val getUser = SharedPreferencesManager.getUserData(this)
        val user = getUser?.split(",")
        if(user != null) {
            userEmail = user[0]
            userController.getUser(this, userEmail) {
                userId = it.id
            }
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
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.length >= 8) {
                    getTouristData()
                } else {
                    cleanFieldsWithNumDocument()
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
            var birthdate = if(selectedDateBirthDayTextView.visibility == View.VISIBLE
                && selectDateBirthDayButton.visibility == View.VISIBLE) {
                selectedDateBirthDayTextView.text.toString()
            } else {
                binding.etFechaNacimiento.text.toString()
            }

            val phone = binding.etTelefono.text.toString()
            val email = binding.etEmail.text.toString()

            if(typeDocument == "DNI") {
                if(numDocument.length != 8) {
                    binding.etNumeroDocumento.error = "Número de documento no válido."
                    Toast.makeText(this, "Número de documento no válido. Debe tener 8 caracteres.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            if(!isValidDateDateTimeFormatter(birthdate)){
                birthdate = dateBirth
            }

            if (!isPhoneValid(phone)) {
                binding.etTelefono.error = "Número de teléfono no válido."
                Toast.makeText(this, "Número de teléfono no válido. Debe tener 9 caracteres y comenzar con un 9.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isEmailValid(email)) {
                binding.etEmail.error = "Correo electrónico no válido"
                Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
                        } else {
                            val reservation = ReservationRequest(userId, idTrip, dayOut, numPassenger.toInt(),"Niubiz", "NoPaid","Failed")
                            reservationController.createReservation(this, reservation) {
                                if(it != "") {
                                    Toast.makeText(this, "Realice pago para confirmar la reserva", Toast.LENGTH_SHORT).show()
                                    val i = Intent(this, NiubizActivity::class.java)
                                    i.putExtra("idTrip", idTrip)
                                    i.putExtra("userId", userId)
                                    i.putExtra("reservation_id", it)
                                    startActivity(i)
                                    finish()
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun cleanFields() {
        binding.etNumeroDocumento.text.clear()
        binding.etNombres.text.clear()
        binding.etApellidoPaterno.text.clear()
        binding.etApellidoMaterno.text.clear()
        binding.selectedDateTextView.text = "Fecha de salida: "
        binding.etFechaNacimiento.text.clear()
        binding.etTelefono.text.clear()
        binding.etEmail.text.clear()
    }

    @SuppressLint("SetTextI18n")
    private fun cleanFieldsWithNumDocument() {
        binding.etNombres.text.clear()
        binding.etApellidoPaterno.text.clear()
        binding.etApellidoMaterno.text.clear()
        binding.selectedDateTextView.text = "Fecha de salida: "
        binding.selectedDateBirthDayTextView.text = "Fecha de nacimiento: "
        binding.etFechaNacimiento.text.clear()
        binding.etTelefono.text.clear()
        binding.etEmail.text.clear()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTouristData() {
        touristController.getTourist(this, binding.etNumeroDocumento.text.toString()) { tourist ->
            if(tourist.email != "") {

                etFechaNacimiento.visibility = View.VISIBLE
                edadTextView.visibility = View.VISIBLE
                selectedDateBirthDayTextView.visibility = View.GONE
                selectDateBirthDayButton.visibility = View.GONE

                dateBirth = tourist.fechaNacimiento

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
            } else {
                cleanFieldsWithNumDocument()
                etFechaNacimiento.visibility = View.GONE
                edadTextView.visibility = View.GONE
                selectedDateBirthDayTextView.visibility = View.VISIBLE
                selectDateBirthDayButton.visibility = View.VISIBLE
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

    private fun showPastDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(selectedYear, selectedMonth, selectedDay)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateString = dateFormat.format(selectedDate.time)

            binding.selectedDateBirthDayTextView.text = dateString
        }, year, month, day)

        datePickerDialog.datePicker.maxDate = calendar.timeInMillis

        datePickerDialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isValidDateDateTimeFormatter(date: String): Boolean {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            LocalDate.parse(date, formatter)
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }

    private fun isPhoneValid(phone: String): Boolean {
        val phonePattern = "^9\\d{8}\$"
        return Pattern.matches(phonePattern, phone)
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return Pattern.matches(emailPattern, email)
    }

}