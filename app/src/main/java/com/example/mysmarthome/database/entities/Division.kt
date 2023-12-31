package com.example.mysmarthome.database.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Division(
    val idF: String,
    val idDivisionHome: Int,
    val name: String,
    val image: String,
){
    @PrimaryKey (autoGenerate = true)
    var idDivision: Int=0
}
