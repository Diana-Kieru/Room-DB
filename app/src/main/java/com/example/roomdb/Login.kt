package com.example.roomdb

import UserViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class Login : AppCompatActivity() {
    private lateinit var loginBtn: Button
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var viewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_login)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Initialize the EditText fields
        emailField = findViewById(R.id.editTextEmail)
        passwordField = findViewById(R.id.editTextPassword)

        // Initialize the Button and set the OnClickListener
        loginBtn = findViewById(R.id.buttonLogin)
        loginBtn.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                // Authenticate the user with the ViewModel
                viewModel.loginUser(email, password)
            }
        }

        // Observe the login result
        viewModel.loginResult.observe(this, Observer { isSuccess ->
            if (isSuccess) {
                // If authentication is successful, redirect to AddEditActivity
                val intent = Intent(this, AddEditActivity::class.java)
                startActivity(intent)
            }
        })
    }

    fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
