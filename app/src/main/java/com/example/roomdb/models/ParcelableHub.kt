package com.example.roomdb.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelableHub(
    val id: Int,
    val hubName: String,
    val hubCode: String,
    val address: String,
    val yearEstablished: String,
    val ownership: String,
    val floorSize: String,
    val facilities: String,
    val inputCenter: String,
    val typeOfBuilding: String,
    val longitude: String,
    val latitude: String,
    val region: String,
    val keyContacts: String,
    val userId: String
) : Parcelable