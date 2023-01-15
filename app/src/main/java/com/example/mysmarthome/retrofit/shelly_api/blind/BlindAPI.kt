package com.example.mysmarthome.retrofit.shelly_api.blind

import com.example.mysmarthome.retrofit.data_models.blind.Blind
import com.example.mysmarthome.retrofit.data_models.blind.BlindMeters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlindAPI {

    @GET("/status")
    suspend fun getBlindStatus(): Response<Blind>

    @GET("/roller/0")
    suspend fun getBlind(): Response<Blind>

    @GET("/roller/0?")
    suspend fun getBlindActions(
        @Query("go") go: String,
        @Query("roller_pos") roller_pos: Int?,
    ): Response<Blind>

    @GET("/meters/{meter_id}")
    suspend fun getBlindMeter(
        @Path("meter_id") meter_id: Int
    ): Response<BlindMeters>

}