package com.example.roomdb.network

import RegisterResponse
import com.example.roomdb.models.Hub
import com.example.roomdb.models.HubResponse
import com.example.roomdb.models.LoginRequest
import com.example.roomdb.models.LoginResponse
import com.example.roomdb.models.ParcelableHub
import com.example.roomdb.models.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("hubs")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @GET("hubs")
    suspend fun getHubs(@Header("Authorization") token: String): Response<HubResponse>


    @DELETE("hubs/{id}")
    suspend fun deleteHub(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ResponseBody>

    @PATCH("hubs/{id}")
    suspend fun updateHub(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body hub: ParcelableHub
    ): Response<Hub>
}



