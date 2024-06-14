package com.example.roomdb

import HubAdapter
import RestClient.apiService
import android.content.Intent
import android.os.Bundle
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
        hubAdapter = HubAdapter(emptyList())

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
        lifecycleScope.launch {
            try {
                val response = apiService.getHubs()
                if (response.isSuccessful) {
                    val hubList = response.body()?.forms
                    if (hubList != null) {
                        setupRecyclerView(hubList)
                        // Update the adapter with the new list
                        hubAdapter.updateList(hubList)
                    }

                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle network error
            }
        }
    }

    private fun setupRecyclerView(hubList: List<Hub>) {
        hubAdapter.updateList(hubList)
    }
}
