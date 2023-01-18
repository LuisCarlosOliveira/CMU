package com.example.mysmarthome.retrofit.shelly_api.light

import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LightAPI {

    @GET("/status")
    suspend fun getLightStatus(): Response<Light>

    @GET("/light/0")
    suspend fun getLight(): Response<Light>

    @GET("/light/0?")
    suspend fun getLightTimer(
        @Query("turn") turn: String,
        @Query("timer") timer: Int?,
    ): Response<Light>

    @GET("/light/0?")
    suspend fun getLightActionsWhiteMode(
        @Query("turn") turn: String,
        @Query("mode") mode: String,
        @Query("white") white: Int,
        @Query("brightness") brightness: Int,
        @Query("timer") timer: Int?
    ): Response<Light>

    @GET("/light/0?")
    suspend fun getLightActionsColorMode(
        @Query("turn") turn: String,
        @Query("mode") mode: String,
        @Query("red") red: Int,
        @Query("green") green: Int,
        @Query("blue") blue: Int,
        @Query("white") white: Int,
        @Query("gain") gain: Int,
        @Query("timer") timer: Int?
    ): Response<Light>

    @GET("/meters/{meter_id}")
    suspend fun getLightMeter(
        @Path("meter_id") meter_id: Int
    ): Response<LightMeters>

}