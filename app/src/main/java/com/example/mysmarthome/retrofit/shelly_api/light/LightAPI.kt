package com.example.mysmarthome.retrofit.shelly_api.light

import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import com.example.mysmarthome.retrofit.data_models.light.LightsData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LightAPI {

    @GET("/light/0")
    fun getLight() : Call<Light>

    @GET("/meter/:meter_id")
    suspend fun getLightMeter() : Call<LightMeters>

}