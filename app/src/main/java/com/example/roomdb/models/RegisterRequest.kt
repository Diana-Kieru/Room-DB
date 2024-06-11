package com.example.roomdb.models

data class RegisterRequest(
    val region: String,
    val hub_name: String,
    val hub_code: String,
    val address: String,
    val year_established: String, // Consider using a Date type if possible
    val ownership: String,
    val floor_size: String,
    val facilities: String,
    val input_center: String,
    val type_of_building: String,
    val longitude: String,
    val latitude: String,
    val key_contacts: List<KeyContact>
)

data class KeyContact(
    val other_name: String,
    val last_name: String,
    val gender: String,
    val role: String,
    val date_of_birth: String, // Consider using a Date type if possible
    val email: String,
    val phone_number: Int,
    val id_number: Int
)