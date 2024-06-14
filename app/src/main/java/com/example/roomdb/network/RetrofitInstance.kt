package com.example.roomdb.network

import android.content.Context
import com.example.roomdb.utils.AuthInterceptor
import com.example.roomdb.utils.SharedPrefs
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance(context: Context) {

    companion object {
        private var instance: Retrofit? = null

        fun getInstance(context: Context): Retrofit {
            if (instance == null) {
                instance = RetrofitInstance(context).retrofit
            }
            return instance!!
        }
    }

    private val sharedPrefs = SharedPrefs(context)
    private val authInterceptor = AuthInterceptor(sharedPrefs)

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://deploy-run.onrender.com") // replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}