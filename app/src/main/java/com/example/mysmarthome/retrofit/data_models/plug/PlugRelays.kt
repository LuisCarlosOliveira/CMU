package com.example.mysmarthome.retrofit.data_models.plug

data class PlugRelays (

    val is_on: Boolean,
    val has_timer: Boolean,
    val timer_started: Long,
    val timer_duration: Int,
    val timer_remaining: Int,
    val overpower: Boolean

) { }