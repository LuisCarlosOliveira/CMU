package com.example.mysmarthome.retrofit.shelly_api.blind

import com.example.mysmarthome.retrofit.data_models.blind.BlindMeters
import com.example.mysmarthome.retrofit.data_models.blind.BlindRoller
import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import com.example.mysmarthome.retrofit.data_models.light.LightsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlindAPI {

    @GET("/roller/0")
    suspend fun getBlindRoller(

        @Query("state") state: String,
        @Query("power") power: Float,
        @Query("is_valid") is_valid: Boolean,
        @Query("overtemperature") overtemperature: Boolean,
        @Query("stop_reason") stop_reason: String,
        @Query("last_direction") last_direction: String,
        @Query("current_pos") current_pos: Int,
        @Query("calibrating") calibrating: Boolean,
        @Query("positioning") positioning: Boolean

    ): Response<BlindRoller>


    @GET("/meter/:meter_id")
    suspend fun getLightMeter(

        @Query("power") power: Float,
        @Query("overpower") overpower: Float,
        @Query("is_valid") is_valid: Boolean,
        @Query("total") total: Float,

    ): Response<BlindMeters>
}