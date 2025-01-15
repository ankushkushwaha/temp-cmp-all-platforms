package com.jetbrains.kmpapp

import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.endEditing
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.dispatch_after
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time

actual open class ToastManager {
    actual fun showToast(message: String) {
        // Dismiss the keyboard if it's open
        dismissKeyboard()

        val alert = UIAlertController.alertControllerWithTitle(
            title = null,
            message = message,
            preferredStyle = UIAlertControllerStyleAlert
        )

        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
        rootViewController?.presentViewController(alert, animated = true, completion = null)

        // Dismiss the alert after 2 seconds to mimic a toast
        val delay = dispatch_time(DISPATCH_TIME_NOW, 600)
        dispatch_after(delay, dispatch_get_main_queue()) {
            alert.dismissViewControllerAnimated(true, completion = null)
        }
    }

    //Function to dismiss the keyboard
    private fun dismissKeyboard() {
        val keyWindow = UIApplication.sharedApplication.keyWindow
        keyWindow?.endEditing(true)
    }
}