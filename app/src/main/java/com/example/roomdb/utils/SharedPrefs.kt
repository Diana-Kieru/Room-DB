package com.example.roomdb.utils
import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {
    private val PREFS_NAME = "com.example.roomdb.prefs"
    private val TOKEN_KEY = "token"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }
}