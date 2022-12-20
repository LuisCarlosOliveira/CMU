package com.example.mysmarthome.retrofit.data_models.light

import com.example.mysmarthome.retrofit.data_models.WifiSta

data class Light (
    val wifi_sta: WifiSta,
    val lights: List<LightsData>,
    val meters: List<LightMeters>,
    val temperature: Float,
    val overtemperature: Boolean
) { }
