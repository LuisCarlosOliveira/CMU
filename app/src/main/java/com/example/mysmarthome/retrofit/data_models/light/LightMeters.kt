package com.example.mysmarthome.retrofit.data_models.light

data class LightMeters (

    val power: Float,

    val is_valid: Boolean,

    val timestamp: Int,

    val total: Float

) { }