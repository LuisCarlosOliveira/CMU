package com.example.mysmarthome.retrofit.data_models.light

data class Light (

    val wifiConnection: Boolean,

    val lightID: String,

    val lightIP: String,

    val lights: List<LightsData>,

    val meters: List<LightMeters>,

    val temperature: Float,

    val overTemperature: Boolean

) { }
