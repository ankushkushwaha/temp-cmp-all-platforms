package com.jetbrains.kmpapp

expect open class NotificationManager() {
    fun showNotification(title: String, description: String)
}