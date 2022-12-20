package com.example.mysmarthome.retrofit.data_models.blind

import com.example.mysmarthome.retrofit.data_models.WifiSta

data class Blind (
    val wifi_sta: WifiSta,
    val rollers: List<BlindRoller>,
    val meters: List<BlindMeters>,
    val temperature: Float,
    val overtemperature: Boolean
) { }
