package com.jetbrains.kmpapp.Utils

import android.Manifest
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.Location
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import com.google.android.gms.location.*

class AndroidLocationManager(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationRequest = LocationRequest.create().apply {
        interval = 10000 // Desired interval for location updates (10 seconds)
        fastestInterval = 5000 // Fastest interval at which location updates can occur
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY // High accuracy
    }

    // Check permission status
    fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Fetch current location
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(onResult: (Location?) -> Unit) {
        if (checkLocationPermission()) {
            // Request a fresh location
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        fusedLocationClient.removeLocationUpdates(this) // Stop updates after the first result
                        onResult(locationResult.lastLocation) // Return the latest location
                    }

                    override fun onLocationAvailability(locationAvailability: LocationAvailability) {
                        if (!locationAvailability.isLocationAvailable) {
                            onResult(null) // Location unavailable
                        }
                    }
                },
                Looper.getMainLooper()
            )
        } else {
            onResult(null) // Permission not granted
        }
    }
}

