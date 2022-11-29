package com.example.mysmarthome.database.entities

import androidx.room.ColumnInfo


data class Address(
    val street: String,
    @ColumnInfo(name="post_code") val postalCode: String,
    val city: String,
    val country: String
)
