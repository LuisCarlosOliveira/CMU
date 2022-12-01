package com.example.mysmarthome.retrofit.data_models.blind

data class Blind (

    val wifiConnection: Boolean,

    val blindID: String,

    val blindIP: String,

    val rollers: List<BlindRoller>,

    val meters: List<BlindMeters>

) { }
