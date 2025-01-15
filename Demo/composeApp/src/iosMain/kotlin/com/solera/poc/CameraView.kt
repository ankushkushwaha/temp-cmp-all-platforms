package com.jetbrains.kmpapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import platform.UIKit.*
import platform.darwin.NSObject
import platform.posix.memcpy
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image


private var retainedDelegate: NSObject? = null

@Composable
actual fun CameraView(result: (Any?) -> Unit) {
    val viewController = getViewController()

    LaunchedEffect(Unit) {
        openCamera(viewController, result)
    }

    DisposableEffect(Unit) {
        onDispose {
            // Release the retained delegate when CameraView is disposed
            retainedDelegate = null
        }
    }
}

private fun openCamera(viewController: UIViewController?, result: (Any?) -> Unit) {
    val imagePickerController = UIImagePickerController()
    imagePickerController.sourceType =
        UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypeCamera

    val delegate = object : NSObject(), UIImagePickerControllerDelegateProtocol, UINavigationControllerDelegateProtocol {
        override fun imagePickerController(
            picker: UIImagePickerController,
            didFinishPickingMediaWithInfo: Map<Any?, *>
        ) {
            picker.dismissViewControllerAnimated(true) { null }
            val image = didFinishPickingMediaWithInfo[UIImagePickerControllerOriginalImage] as? UIImage
            val bitmap = image?.toByteArray(80.0)?.toImageBitmap()
            result(bitmap)
        }

        override fun imagePickerControllerDidCancel(picker: UIImagePickerController) {
            picker.dismissViewControllerAnimated(true) { null }
            result(null)
        }
    }

    retainedDelegate = delegate // Retain the delegate to prevent garbage collection
    imagePickerController.delegate = delegate

    viewController?.presentViewController(imagePickerController, animated = true, completion = null)
}

// Helper function to get the top UIViewController
private fun getViewController(): UIViewController? {
    val window = UIApplication.sharedApplication.keyWindow
    var viewController = window?.rootViewController
    while (viewController?.presentedViewController != null) {
        viewController = viewController.presentedViewController
    }
    return viewController
}

@OptIn(ExperimentalForeignApi::class)
private fun UIImage.toByteArray(compressionQuality: Double): ByteArray {
    val validCompressionQuality = compressionQuality.coerceIn(0.0, 1.0)
    val jpegData = UIImageJPEGRepresentation(this, validCompressionQuality)!!
    return ByteArray(jpegData.length.toInt()).apply {
        memcpy(this.refTo(0), jpegData.bytes, jpegData.length)
    }
}

 fun ByteArray.toImageBitmap() = Image.makeFromEncoded(this).toComposeImageBitmap()

