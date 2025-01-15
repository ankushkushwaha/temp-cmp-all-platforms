package com.jetbrains.kmpapp

import android.widget.Toast

actual open class ToastManager actual constructor() {
    actual fun showToast(message: String) {
        // Get the context from somewhere appropriate, like an Application or Activity
        val context = MuseumApp.getContext() // Replace with your actual context retrieval
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}


