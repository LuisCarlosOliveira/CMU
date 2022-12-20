package com.example.mysmarthome.retrofit.data_models.plug

data class PlugMeters (
    val power: Float,
    val overpower: Float,
    val total: Float
) { }