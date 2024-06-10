package com.example.roomdb.data



@androidx.room.Entity(tableName = "users")
data class User(
    @androidx.room.PrimaryKey(autoGenerate = true)
    val id: Int,
    val fisrtName: String,
    val lastName: String,
    val email: String) {
}