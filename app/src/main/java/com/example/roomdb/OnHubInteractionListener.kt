package com.example.roomdb

import com.example.roomdb.models.Hub

interface OnHubInteractionListener {
    fun onHubDeleted(hub: Hub)
}