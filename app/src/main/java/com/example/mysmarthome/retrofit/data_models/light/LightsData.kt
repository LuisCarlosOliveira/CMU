package com.example.mysmarthome.retrofit.data_models.light

data class LightsData (

    val ison: Boolean,
    val has_timer: Boolean,
    val timer_started: Long,
    val timer_duration: Int,
    val timer_remaining: Int,
    val mode: String,
    val red: Int,
    val blue: Int,
    val green:Int,
    val white: Int,
    val gain: Int,
    val brightness: Int

) { }
