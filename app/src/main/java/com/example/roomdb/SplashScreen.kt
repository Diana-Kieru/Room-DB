package com.example.roomdb


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var buttonGetStarted: Button
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        buttonGetStarted = findViewById(R.id.buttonGetStarted)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonGetStarted.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}