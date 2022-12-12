package com.example.mysmarthome.database.entities

import androidx.room.PrimaryKey
import com.example.mysmarthome.retrofit.data_models.blind.BlindMeters
import com.example.mysmarthome.retrofit.data_models.blind.BlindRoller

data class Blind(

    @PrimaryKey
    val ssid: String,

    val idDeviceBlind: Int,

    val rollers: List<BlindRoller>,

    val meters: List<BlindMeters>

) { }