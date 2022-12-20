package com.example.mysmarthome.retrofit.data_models.plug
import com.example.mysmarthome.retrofit.data_models.WifiSta

data class Plug (
    val wifi_sta: WifiSta,
    val relays: List<PlugRelays>,
    val meters: List<PlugMeters>,
    val temperature: Float,
    val overtemperature: Boolean
) { }

