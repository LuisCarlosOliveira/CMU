package com.example.mysmarthome.retrofit.shelly_api.plug

import com.example.mysmarthome.retrofit.data_models.plug.Plug
import com.example.mysmarthome.retrofit.data_models.plug.PlugMeters
import retrofit2.Call
import retrofit2.http.GET

interface PlugAPI {

    @GET("/relay/0")
    fun getPlugRelay() : Call<Plug>


    @GET("/meter/:meter_id")
    suspend fun getPlugMeter() : Call<PlugMeters>

}