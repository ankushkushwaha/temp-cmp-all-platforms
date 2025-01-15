package com.jetbrains.kmpapp

expect open class ToastManager() {
    fun showToast(message: String)
}