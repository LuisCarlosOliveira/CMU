package com.example.mysmarthome.retrofit.data_models.plug

data class Plug (

    val wifiConnection: Boolean,

    val plugID: String,

    val plugIP: String,

    val relays: List<PlugRelays>,

    val meters: List<PlugMeters>,

    val temperature: Float,

    val overtemperature: Boolean

) { }

