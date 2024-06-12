package com.example.roomdb

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb.data.UserViewModel

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
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            viewModel.loginUser(email, password)
        }
    }
}