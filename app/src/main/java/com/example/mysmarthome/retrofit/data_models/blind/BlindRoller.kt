package com.example.mysmarthome.retrofit.data_models.blind

data class BlindRoller(

    val state: String,
    val power: Float,
    val overtemperature: Boolean,
    val stop_reason: String,
    val last_direction: String,
    val current_pos: Int

) { }
