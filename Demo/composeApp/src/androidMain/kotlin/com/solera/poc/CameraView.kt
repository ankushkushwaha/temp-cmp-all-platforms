package com.jetbrains.kmpapp

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.asImageBitmap
import coil3.Bitmap

@Composable
actual fun CameraView(result: (Any?) -> Unit) {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    val launcherSelfie = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val imageBitmap = activityResult.data?.extras?.get("data") as? Bitmap
            imageBitmap?.let {
                result(it.asImageBitmap())
            }
        } else {
            // If user press back button before capturing the image, the imageBitmap will be null
            result(null)
        }
    }
    LaunchedEffect(Unit) {
        launcherSelfie.launch(intent)
    }
}

