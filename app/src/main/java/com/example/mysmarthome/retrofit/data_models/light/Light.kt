package com.example.mysmarthome.retrofit.data_models.light

data class Light (
    val lights: List<LightsData>,
    val wifi_sta: WifiSta,
    val temperature: Float,
    val overtemperature: Boolean
) { }
