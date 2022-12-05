package com.example.mysmarthome.database.entities

data class BlindRoller(

    val state: String,

    val power: Float,

    val is_valid: Boolean,

    val overtemperature: Boolean,

    val stop_reason: String,

    val last_direction: String,

    val current_pos: Int,

    val calibrating: Boolean,

    val positioning: Boolean

){ }
