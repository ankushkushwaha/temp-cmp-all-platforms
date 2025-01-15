package com.jetbrains.kmpapp.Utils

import android.content.Context
import com.solera.poc.MuseumApp.Companion.getContext
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class LocationManager actual constructor() {

    private var context: Context = getContext()
    private var androidLocationManager: AndroidLocationManager = AndroidLocationManager(context)

    actual suspend fun requestCurrentLocation(): Result<com.jetbrains.kmpapp.Utils.Location> {
        return suspendCancellableCoroutine { continuation ->
            if (androidLocationManager.checkLocationPermission() == true) {
                androidLocationManager.getCurrentLocation { location ->
                    if (location != null) {
                        continuation.resume(Result.success(Location(latitude = location.latitude, longitude = location.longitude)))
                    } else {
                        continuation.resumeWithException(Exception("Location not available"))
                    }
                }
            } else {
                continuation.resumeWithException(Exception("Permission not granted"))
            }
        }
    }
}
