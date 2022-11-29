package com.example.mysmarthome.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.enums.TypeDevice

@Entity
data class Device(

    @PrimaryKey val idDevice: Int,
    val name: String,
    val status: Boolean,
    val connected: Boolean,
    val type: TypeDevice,

)
