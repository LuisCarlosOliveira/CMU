package com.example.mysmarthome.interfaces
//gives a response everytime there is a modification of the value (location in this case)
import kotlinx.coroutines.flow.Flow

import android.location.Location

interface LocationUser {

    fun getLocationUpdates(interval: Long): Flow<Location>

    class LocationException(message: String): Exception()
}