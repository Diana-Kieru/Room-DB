package com.example.roomdb

import RegisterResponse
import RestClient
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.models.KeyContact
import com.example.roomdb.models.RegisterRequest
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Registration : AppCompatActivity() {

    private lateinit var etLatitude: TextInputEditText
    private lateinit var etHubName: TextInputEditText
    private lateinit var etHubCode: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var etYearEstablished: TextInputEditText
    private lateinit var etOwnership: TextInputEditText
    private lateinit var etFloorSize: TextInputEditText
    private lateinit var etFacilities: TextInputEditText
    private lateinit var etInputCenter: TextInputEditText
    private lateinit var etTypeOfBuilding: TextInputEditText
    private lateinit var etLongitude: TextInputEditText
    private lateinit var etRegion: TextInputEditText
    private lateinit var etKeyContactFirstName: TextInputEditText
    private lateinit var etKeyContactLastName: TextInputEditText
    private lateinit var etKeyContactGender: TextInputEditText
    private lateinit var etKeyContactRole: TextInputEditText
    private lateinit var etKeyContactDateOfBirth: TextInputEditText
    private lateinit var etKeyContactEmail: TextInputEditText
    private lateinit var etKeyContactPhoneNumber: TextInputEditText
    private lateinit var etKeyContactIdNumber: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        etLatitude = findViewById(R.id.etLatitude)
        etHubName = findViewById(R.id.etHubName)
        etHubCode = findViewById(R.id.etHubCode)
        etAddress = findViewById(R.id.etAddress)
        etYearEstablished = findViewById(R.id.etYearEstablished)
        etOwnership = findViewById(R.id.etOwnership)
        etFloorSize = findViewById(R.id.etFloorSize)
        etFacilities = findViewById(R.id.etFacilities)
        etInputCenter = findViewById(R.id.etInputCenter)
        etTypeOfBuilding = findViewById(R.id.etTypeOfBuilding)
        etLongitude = findViewById(R.id.etLongitude)
        etRegion = findViewById(R.id.etRegion)
        etKeyContactFirstName = findViewById(R.id.etKeyContactFirstName)
        etKeyContactLastName = findViewById(R.id.etKeyContactLastName)
        etKeyContactGender = findViewById(R.id.etKeyContactGender)
        etKeyContactRole = findViewById(R.id.etKeyContactRole)
        etKeyContactDateOfBirth = findViewById(R.id.etKeyContactDateOfBirth)
        etKeyContactEmail = findViewById(R.id.etKeyContactEmail)
        etKeyContactPhoneNumber = findViewById(R.id.etKeyContactPhoneNumber)
        etKeyContactIdNumber = findViewById(R.id.etKeyContactIdNumber)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            registerUser()
        }

        etYearEstablished.setOnClickListener {
            showDatePickerDialog(etYearEstablished, "yyyy")
        }

        etKeyContactDateOfBirth.setOnClickListener {
            showDatePickerDialog(etKeyContactDateOfBirth, "dd/MM/yyyy")
        }
    }

    private fun showDatePickerDialog(editText: TextInputEditText, format: String) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(selectedYear, selectedMonth, selectedDay)
            val date = SimpleDateFormat(format, Locale.getDefault()).format(selectedDate.time)
            editText.setText(date)
        }, year, month, day).show()
    }

    private fun validateFields(): Boolean {
        // Validate all the fields here
        if (etLatitude.text.isNullOrEmpty()) {
            etLatitude.error = "Latitude is required"
            return false
        }
        if (etHubName.text.isNullOrEmpty()) {
            etHubName.error = "Hub Name is required"
            return false
        }
        if (etHubCode.text.isNullOrEmpty()) {
            etHubCode.error = "Hub Code is required"
            return false
        }
        if (etAddress.text.isNullOrEmpty()) {
            etAddress.error = "Address is required"
            return false
        }
        if (etYearEstablished.text.isNullOrEmpty()) {
            etYearEstablished.error = "Year Established is required"
            return false
        }
        if (etOwnership.text.isNullOrEmpty()) {
            etOwnership.error = "Ownership is required"
            return false
        }
        if (etFloorSize.text.isNullOrEmpty()) {
            etFloorSize.error = "Floor Size is required"
            return false
        }
        if (etFacilities.text.isNullOrEmpty()) {
            etFacilities.error = "Facilities are required"
            return false
        }
        if (etInputCenter.text.isNullOrEmpty()) {
            etInputCenter.error = "Input Center is required"
            return false
        }
        if (etTypeOfBuilding.text.isNullOrEmpty()) {
            etTypeOfBuilding.error = "Type of Building is required"
            return false
        }
        if (etLongitude.text.isNullOrEmpty()) {
            etLongitude.error = "Longitude is required"
            return false
        }
        if (etRegion.text.isNullOrEmpty()) {
            etRegion.error = "Region is required"
            return false
        }
        if (etKeyContactFirstName.text.isNullOrEmpty()) {
            etKeyContactFirstName.error = "First Name is required"
            return false
        }
        if (etKeyContactLastName.text.isNullOrEmpty()) {
            etKeyContactLastName.error = "Last Name is required"
            return false
        }
        if (etKeyContactGender.text.isNullOrEmpty()) {
            etKeyContactGender.error = "Gender is required"
            return false
        }
        if (etKeyContactRole.text.isNullOrEmpty()) {
            etKeyContactRole.error = "Role is required"
            return false
        }
        if (etKeyContactDateOfBirth.text.isNullOrEmpty()) {
            etKeyContactDateOfBirth.error = "Date of Birth is required"
            return false
        }
        if (etKeyContactEmail.text.isNullOrEmpty()) {
            etKeyContactEmail.error = "Email is required"
            return false
        }
        if (etKeyContactPhoneNumber.text.isNullOrEmpty()) {
            etKeyContactPhoneNumber.error = "Phone Number is required"
            return false
        }
        if (etKeyContactIdNumber.text.isNullOrEmpty()) {
            etKeyContactIdNumber.error = "ID Number is required"
            return false
        }

        // If all validations pass, return true
        return true
    }

    private fun registerUser() {
        if (validateFields()) {
            val dobInputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val yearInputFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val dobDate = dobInputFormat.parse(etKeyContactDateOfBirth.text.toString())
            val yearDate = yearInputFormat.parse(etYearEstablished.text.toString())
            val formattedDob = outputFormat.format(dobDate)
            val formattedYear = outputFormat.format(yearDate)

            val registerRequest = RegisterRequest(
                region = etRegion.text.toString(),
                hub_name = etHubName.text.toString(),
                hub_code = etHubCode.text.toString(),
                address = etAddress.text.toString(),
                year_established = formattedYear,
                ownership = etOwnership.text.toString(),
                floor_size = etFloorSize.text.toString(),
                facilities = etFacilities.text.toString(),
                input_center = etInputCenter.text.toString(),
                type_of_building = etTypeOfBuilding.text.toString(),
                longitude = etLongitude.text.toString(),
                latitude = etLatitude.text.toString(),
                key_contacts = listOf(
                    KeyContact(
                        other_name = etKeyContactFirstName.text.toString(),
                        last_name = etKeyContactLastName.text.toString(),
                        gender = etKeyContactGender.text.toString(),
                        role = etKeyContactRole.text.toString(),
                        date_of_birth = formattedDob,
                        email = etKeyContactEmail.text.toString(),
                        phone_number = etKeyContactPhoneNumber.text.toString().toIntOrNull() ?: 0,
                        id_number = etKeyContactIdNumber.text.toString().toIntOrNull() ?: 0
                    )
                )
            )
            val apiService = RestClient.getApiService(applicationContext)
            val call = apiService.registerUser(registerRequest)

            call.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        // Registration successful
                        showToastMessage("Registration successful")
                        val intent = Intent(this@Registration, AddEditActivity::class.java)
                        response.body()?.let {
                            intent.putExtra("hub_data", it)
                        }
                        startActivity(intent)
                    } else {
                        // Registration failed
                        showToastMessage("Registration failed: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    // Network error
                    showToastMessage("Network error: ${t.message}")
                }
            })
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
