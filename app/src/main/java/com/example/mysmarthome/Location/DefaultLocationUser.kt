package com.example.mysmarthome.Location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.example.mysmarthome.interfaces.LocationUser
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import hasLocationPermission
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class DefaultLocationClient(
    private val context: Context,
    private val user: FusedLocationProviderClient
): LocationUser {

    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> {
        return callbackFlow {
            if (!context.hasLocationPermission()) {
                throw LocationUser.LocationException("Missing location permission")
            }

            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (!isGpsEnabled && !isNetworkEnabled) {
                throw LocationUser.LocationException("GPS is disabled")
            }

            //location request
            val request = LocationRequest.create()
                .setInterval(interval)
                .setFastestInterval(interval)

            //calback
            val locationCallback = object : LocationCallback() {
                // called everytime user fetch new location
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }
            //starts the flow
            user.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )
            //block de flow
            awaitClose {
                user.removeLocationUpdates(locationCallback)
            }
        }
    }
}