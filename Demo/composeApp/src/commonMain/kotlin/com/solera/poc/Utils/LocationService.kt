package com.jetbrains.kmpapp.Utils

expect class LocationManager() {
    suspend fun requestCurrentLocation(): Result<Location>
}

data class Location(val latitude: Double, val longitude: Double)

enum class LocationPermissionStatus {
    NOT_DETERMINED,
    RESTRICTED_OR_DENIED,
    ACCEPTED
}