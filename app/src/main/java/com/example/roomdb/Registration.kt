package com.example.roomdb

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

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
    private lateinit var  etKeyContactFirstName: TextInputEditText
    private lateinit var  etKeyContactLastName: TextInputEditText
    private lateinit var  etKeyContactGender: TextInputEditText
    private lateinit var  etKeyContactRole: TextInputEditText
    private lateinit var  etKeyContactDateOfBirth: TextInputEditText
    private lateinit var  etKeyContactEmail: TextInputEditText
    private lateinit var  etKeyContactPhoneNumber: TextInputEditText
    private lateinit var  etKeyContactIdNumber: TextInputEditText





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


        setupTextWatchers()

    }

    private fun setupTextWatchers() {
        etLatitude.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    etHubName.visibility = View.VISIBLE
                } else {
                    etHubName.visibility = View.GONE
                }
            }
        })

        etHubName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.isNotEmpty() == true) {
                    etHubCode.visibility = View.VISIBLE
                } else {
                    etHubCode.visibility = View.GONE
                }
            }
        })
    }
}