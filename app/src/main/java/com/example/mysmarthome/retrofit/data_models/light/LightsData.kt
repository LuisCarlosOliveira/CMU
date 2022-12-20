package com.example.mysmarthome.retrofit.data_models.light

data class LightsData (
    val ison: Boolean,
    val has_timer: Boolean,
    val timer_started: Float,
    val timer_duration: Float,
    val timer_remaining: Float,
    val mode: String,
    val red: Int,
    val blue: Int,
    val green:Int,
    val white: Int,
    val gain: Int,
    val brightness: Int
) { }
