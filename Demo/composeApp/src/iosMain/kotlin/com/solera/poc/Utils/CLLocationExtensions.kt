package com.jetbrains.kmpapp.Utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreLocation.CLLocation

@OptIn(ExperimentalForeignApi::class)
fun CLLocation.getLatitude(): Double {
    val coordinate = this.coordinate()
    return coordinate.useContents { latitude }
}

@OptIn(ExperimentalForeignApi::class)
fun CLLocation.getLongitude(): Double {
    val coordinate = this.coordinate()
    return coordinate.useContents { longitude }
}
