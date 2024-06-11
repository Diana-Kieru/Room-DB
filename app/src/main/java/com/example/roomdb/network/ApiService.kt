package com.example.roomdb.network

import com.example.roomdb.models.LoginRequest
import com.example.roomdb.models.LoginResponse
import com.example.roomdb.models.RegisterRequest
import com.example.roomdb.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
    @POST("register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}