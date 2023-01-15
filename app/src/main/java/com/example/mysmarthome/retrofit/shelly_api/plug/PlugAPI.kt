package com.example.mysmarthome.retrofit.shelly_api.plug

import com.example.mysmarthome.retrofit.data_models.plug.Plug
import com.example.mysmarthome.retrofit.data_models.plug.PlugMeters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlugAPI {

    @GET("/status")
    suspend fun getPlugStatus(): Response<Plug>

    @GET("/relay/0")
    suspend fun getPlug(): Response<Plug>

    @GET("/relay/0?")
    suspend fun getPlugTimer(
        @Query("turn") turn: String,
        @Query("timer") timer: Int?,
    ): Response<Plug>

    @GET("/meters/{meter_id}")
    suspend fun getPlugMeter(
        @Path("meter_id") meter_id: Int
    ): Response<PlugMeters>

}