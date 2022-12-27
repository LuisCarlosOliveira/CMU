package com.example.mysmarthome.retrofit.shelly_api.light

import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import retrofit2.Call
import retrofit2.http.GET

interface LightAPI {

    @GET("/light/0")
    fun getLight() : Call<Light>

    @GET("/meter/:meter_id")
    suspend fun getLightMeter() : Call<LightMeters>
}