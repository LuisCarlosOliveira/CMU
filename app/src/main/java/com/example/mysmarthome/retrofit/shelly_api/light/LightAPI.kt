package com.example.mysmarthome.retrofit.shelly_api.light

import com.example.mysmarthome.retrofit.data_models.light.Light
import com.example.mysmarthome.retrofit.data_models.light.LightMeters
import com.example.mysmarthome.retrofit.data_models.light.LightsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LightAPI {

    @GET("/light/0")
    suspend fun getLight(

        @Query("wifiConnection") wifiConnection: Boolean,
        @Query("lightID") lightID: String,
        @Query("lightIP") lightIP: String,
        @Query("lights") lights: List<LightsData>,
        @Query("meters") meters: List<LightMeters>,
        @Query("temperature") temperature: Float,
        @Query("overTemperature") overTemperature: Boolean

    ) : Response<Light>

    @GET("/meter/:meter_id")
    suspend fun getLightMeter(

        @Query("power") power: Float ,
        @Query("is_valid") is_valid: Boolean ,
        @Query("total") total: Float ,

    ) : Response<LightMeters>

}