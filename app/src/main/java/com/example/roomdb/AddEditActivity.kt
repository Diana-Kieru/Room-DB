package com.example.roomdb

import HubAdapter
import RestClient
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.models.Hub
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class AddEditActivity : AppCompatActivity() {
    private lateinit var fabAddHub: FloatingActionButton
    private lateinit var hubAdapter: HubAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_edit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the adapter with an empty list
        hubAdapter = HubAdapter(emptyList()) { hub ->
            deleteHub(hub.id)
        }

        // Get the reference to the RecyclerView
        recyclerView = findViewById(R.id.rvHubs)
        recyclerView.adapter = hubAdapter

        fetchHubs()

        fabAddHub = findViewById(R.id.fabAddHub)
        fabAddHub.setOnClickListener {
            // Launch the RegistrationActivity
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }


    private fun fetchHubs() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val token = sharedPreferences.getString("accessToken", "") ?: ""
        val apiService = RestClient.getApiService(applicationContext)

        lifecycleScope.launch {
            try {
                val response = apiService.getHubs("Bearer $token")
                if (response.isSuccessful) {
                    val hubList = response.body()?.forms
                    if (hubList != null) {
                        // Sort the hubList in descending order based on the ID
                        val sortedHubList = hubList.sortedByDescending { it.id }

                        setupRecyclerView(sortedHubList)
                        // Update the adapter with the sorted list
                        hubAdapter.updateList(sortedHubList)
                    }
                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle network error
            }
        }
    }

    private fun deleteHub(id: Int) {
        val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val token = sharedPreferences.getString("accessToken", "") ?: ""
        val apiService = RestClient.getApiService(applicationContext)

        lifecycleScope.launch {
            try {
                val response = apiService.deleteHub("Bearer $token", id)
                if (response.isSuccessful) {
                    // Hub deleted successfully
                    showToastMessage("Hub deleted successfully")
                    // Refresh the list of hubs
                    fetchHubs()
                } else {
                    // Handle API error
                    showToastMessage("Failed to delete hub: ${response.message()}")
                }
            } catch (e: Exception) {
                // Handle network error
                showToastMessage("Network error: ${e.message}")
            }
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView(hubList: List<Hub>) {
        hubAdapter.updateList(hubList)
    }
}
