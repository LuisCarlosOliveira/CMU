package com.example.mysmarthome.database.entities

data class BlindMeters(

    val power: Float,

    val overpower: Float,

    val is_valid: Boolean,

    val timestamp: Int,

    val total: Float

){ }