package com.example.mysmarthome.database.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Division(
    @PrimaryKey val idDivision: Int,
    val idDivisionHome: Int,
    val name: String,
    val image: List<Bitmap?>,
)
