package com.example.mysmarthome.retrofit.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val base_url = "http://tour-pedia.org/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}