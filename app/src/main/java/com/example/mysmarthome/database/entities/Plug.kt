package com.example.mysmarthome.database.entities

import androidx.room.PrimaryKey
import com.example.mysmarthome.retrofit.data_models.plug.PlugMeters
import com.example.mysmarthome.retrofit.data_models.plug.PlugRelays

data class Plug(

    @PrimaryKey
    val ssid: String,

    val idDevicePlug: Int,

    val relays: List<PlugRelays>,

    val meters: List<PlugMeters>,

    val temperature: Float,

    val overtemperature: Boolean

){ }
