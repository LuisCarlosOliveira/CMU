package com.example.mysmarthome.retrofit.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val base_url = "http://10.0.2.2:"

    fun getInstance(port:Int): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url+port+"/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}