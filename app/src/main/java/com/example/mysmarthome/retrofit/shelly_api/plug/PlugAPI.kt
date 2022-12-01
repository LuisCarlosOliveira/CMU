package com.example.mysmarthome.retrofit.shelly_api.plug

import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import com.example.mysmarthome.retrofit.data_models.light.LightsData
import com.example.mysmarthome.retrofit.data_models.plug.Plug
import com.example.mysmarthome.retrofit.data_models.plug.PlugMeters
import com.example.mysmarthome.retrofit.data_models.plug.PlugRelays
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlugAPI {

    @GET("/relay/0")
    suspend fun getPlugRelay(

        @Query("wifiConnection") wifiConnection: Boolean,
        @Query("plugID") plugID: String,
        @Query("plugIP") plugIP: String,
        @Query("relays") relays: List<PlugRelays>,
        @Query("meters") meters: List<PlugMeters>,
        @Query("temperature") temperature: Float,
        @Query("overtemperature") overtemperature: Boolean

    ) : Response<Plug>


    @GET("/meter/:meter_id")
    suspend fun getPlugMeter(

        @Query("power") power: Float,
        @Query("is_valid") is_valid: Boolean,
        @Query("total") total: Float,

    ) : Response<PlugMeters>

}