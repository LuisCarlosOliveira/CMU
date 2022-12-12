package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.enums.TypeDevice

@Entity
data class Device(

    val idDivisionDevice: Int,

    val wifiConnection: Boolean,

    val ip: String,

    val type: TypeDevice,

){
    @PrimaryKey (autoGenerate = true)
    var idDevice: Int = 0
}
