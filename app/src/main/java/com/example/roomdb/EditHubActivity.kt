package com.example.roomdb

import RegisterResponse
import RestClient
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdb.models.ParcelableHub
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditHubActivity : AppCompatActivity() {

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
    private lateinit var etLatitude: TextInputEditText
    private lateinit var btnUpdateHub: Button
    private lateinit var etRegion: TextInputEditText

    private var hubData: ParcelableHub? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_hub)

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
        etLatitude = findViewById(R.id.etLatitude)
        btnUpdateHub = findViewById(R.id.btnUpdateHub)
        etRegion = findViewById(R.id.etRegion)

        // Get hub data from intent
        hubData = intent.getParcelableExtra<ParcelableHub>("hubData")

        // Update the UI with the hub data
        if (hubData != null) {
            etHubName.setText(hubData?.hubName)
            etHubCode.setText(hubData?.hubCode)
            etAddress.setText(hubData?.address)
            etYearEstablished.setText(hubData?.yearEstablished)
            etOwnership.setText(hubData?.ownership)
            etFloorSize.setText(hubData?.floorSize)
            etFacilities.setText(hubData?.facilities)
            etInputCenter.setText(hubData?.inputCenter)
            etTypeOfBuilding.setText(hubData?.typeOfBuilding)
            etLongitude.setText(hubData?.longitude)
            etLatitude.setText(hubData?.latitude)
            etRegion.setText(hubData?.region)
        }

        btnUpdateHub.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                val token = sharedPreferences.getString("accessToken", "") ?: ""
                val apiService = RestClient.getApiService(applicationContext)

                try {
                    val updatedHub = updateHub()
                    if (hubData != null) {
                        val hubId = hubData?.id ?: -1
                        val response = apiService.updateHub("Bearer $token", hubId, updatedHub)
                        if (response.isSuccessful) {
                            showToastMessage("Hub updated successfully")
                            finish() // Go back to the previous activity
                        } else {
                            showToastMessage("Failed to update hub: ${response.message()}")
                        }
                    } else {
                        showToastMessage("Hub data not available")
                    }
                } catch (e: Exception) {
                    showToastMessage("Network error: ${e.message}")
                }
            }
        }
    }

    private fun updateHub(): ParcelableHub {
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "") ?: ""
        return hubData?.copy(
            hubName = etHubName.text.toString(),
            hubCode = etHubCode.text.toString(),
            address = etAddress.text.toString(),
            yearEstablished = etYearEstablished.text.toString(),
            ownership = etOwnership.text.toString(),
            floorSize = etFloorSize.text.toString(),
            facilities = etFacilities.text.toString(),
            inputCenter = etInputCenter.text.toString(),
            typeOfBuilding = etTypeOfBuilding.text.toString(),
            longitude = etLongitude.text.toString(),
            latitude = etLatitude.text.toString(),
            region = etRegion.text.toString(),
            keyContacts = "",  // Add a value for keyContacts
            userId = userId // Add a value for userId
        ) ?: ParcelableHub(
            id = -1,
            hubName = etHubName.text.toString(),
            hubCode = etHubCode.text.toString(),
            address = etAddress.text.toString(),
            yearEstablished = etYearEstablished.text.toString(),
            ownership = etOwnership.text.toString(),
            floorSize = etFloorSize.text.toString(),
            facilities = etFacilities.text.toString(),
            inputCenter = etInputCenter.text.toString(),
            typeOfBuilding = etTypeOfBuilding.text.toString(),
            longitude = etLongitude.text.toString(),
            latitude = etLatitude.text.toString(),
            region = etRegion.text.toString(),
            keyContacts = "",
            userId = userId
        )
    }

    private fun showToastMessage(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@EditHubActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}