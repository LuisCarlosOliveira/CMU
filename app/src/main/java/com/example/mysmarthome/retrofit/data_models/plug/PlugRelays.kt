package com.example.mysmarthome.retrofit.data_models.plug

data class PlugRelays (

    val is_on: Boolean,

    val has_timer: Boolean,

    val timer_started: Float,

    val timer_duration: Float,

    val timer_remaining: Float,

    val overpower: Boolean

) { }