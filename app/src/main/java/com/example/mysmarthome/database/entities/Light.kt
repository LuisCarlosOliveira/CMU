package com.example.mysmarthome.database.entities

import androidx.room.PrimaryKey
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import com.example.mysmarthome.retrofit.data_models.light.LightsData

data class Light(

    @PrimaryKey
    val idss: String,

    val idDeviceLight: Int,

    val lights: List<LightsData>,

    val meters: List<LightMeters>,

    val temperature: Float,

    val overTemperature: Boolean

){ }