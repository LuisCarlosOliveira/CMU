package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Division(
    @PrimaryKey val idDivision: Int,
    val name: String,
    val image: String,
)
