package com.example.mysmarthome.retrofit.shelly_api.blind

import com.example.mysmarthome.retrofit.data_models.blind.BlindMeters
import com.example.mysmarthome.retrofit.data_models.blind.BlindRoller
import retrofit2.Call
import retrofit2.http.GET

interface BlindAPI {

    @GET("/roller/0")
    fun getBlindRoller(): Call<BlindRoller>

    @GET("/meter/:meter_id")
    suspend fun getLightMeter(): Call<BlindMeters>
}