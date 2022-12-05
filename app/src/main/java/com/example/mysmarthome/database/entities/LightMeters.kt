package com.example.mysmarthome.database.entities

data class LightMeters(

    val power: Float,

    val is_valid: Boolean,

    val timestamp: Int,

    val total: Float

){ }
