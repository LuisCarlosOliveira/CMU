package com.example.mysmarthome.database.repositories

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import com.example.mysmarthome.Location.LocationDetails
import com.google.android.gms.location.*
import com.google.android.gms.location.Priority.*

class LocationLiveData(var context: Context) : LiveData<LocationDetails>() {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun onActive() {
        super.onActive()
        //used because isnt a UI
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location.also {
                setLocationData(it)
            }
        }
        startLocationUpdates()
    }

    fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    //set location, toString() to avoid float inaccurate, function used everytime there is a new location
    private fun setLocationData(location: Location?) {
        //let to deal with nullity
        location?.let { location ->
            value = LocationDetails(location.longitude.toString(), location.latitude.toString())
        }
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    //creating an object
    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            //if variable is null, return
            locationResult ?: return
            for(location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    companion object{
        val ONE_MINUTE : Long = 6000
        val locationRequest : LocationRequest = LocationRequest.Builder(PRIORITY_HIGH_ACCURACY, ONE_MINUTE)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(ONE_MINUTE /4)
            .setMaxUpdateDelayMillis(ONE_MINUTE )
            .build()
    }
}