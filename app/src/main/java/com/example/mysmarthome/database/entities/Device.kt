package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.enums.TypeDevice

@Entity
data class Device(

    val idDivisionDevice: Int,

    val porta: Int,

    val nome: String,

    val type: String,

){
    @PrimaryKey (autoGenerate = true)
    var idDevice: Int = 0
}
