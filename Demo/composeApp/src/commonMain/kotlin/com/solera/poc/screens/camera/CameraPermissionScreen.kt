package com.jetbrains.kmpapp.screens.camera

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetbrains.kmpapp.CameraView
//import dev.icerock.moko.permissions.PermissionsController
//import dev.icerock.moko.permissions.compose.BindEffect
//import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
//import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.back
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraPermissionScreen(
    onBackClick: () -> Unit
) {
//    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
//    val permissionsController: PermissionsController =
//        remember(factory) { factory.createPermissionsController() }
//
//    BindEffect(permissionsController)
//
//    val viewModel: CameraPermissionViewModel = viewModel {
//        CameraPermissionViewModel(permissionsController)
//    }
//    val state by viewModel.state.collectAsState()
//    var hasPermissionAlready by remember { mutableStateOf(false) }
//    var photoBitmap: ImageBitmap? by remember { mutableStateOf(null) }
//    var isCameraViewActive by remember { mutableStateOf(false) }
//    val scope = rememberCoroutineScope()
//
//    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            when (event) {
//                Lifecycle.Event.ON_CREATE -> {}
//                Lifecycle.Event.ON_START -> {
//                    scope.launch {
//                        hasPermissionAlready = viewModel.checkIfHavePermission()
//                    }
//                }
//                Lifecycle.Event.ON_RESUME -> {
//                    scope.launch {
//                        hasPermissionAlready = viewModel.checkIfHavePermission()
//                    }
//                }
//                Lifecycle.Event.ON_PAUSE -> {}
//                Lifecycle.Event.ON_STOP -> {}
//                Lifecycle.Event.ON_DESTROY -> {}
//                else -> {}
//            }
//        }
//
//        lifecycleOwner.lifecycle.addObserver(observer)
//
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Camera Screen") },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
//                    }
//                },
//                colors = TopAppBarDefaults.mediumTopAppBarColors()
//            )
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//        ) {
//            AnimatedContent(hasPermissionAlready) { hasPermission ->
//                if (hasPermission) {
//                    if (photoBitmap == null && isCameraViewActive.not()) {
//                        CameraView { capturedImage ->
//                            if (capturedImage == null) {
//                                isCameraViewActive = true
//                            } else {
//                                photoBitmap = capturedImage as ImageBitmap
//                            }
//                        }
//                    } else {
//                        // Display the captured image with a button to re-open the camera
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(16.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            verticalArrangement = Arrangement.Center
//                        ) {
//                            if (photoBitmap == null) {
//                                Text(
//                                    text = "Please Open Camera to Capture Image",
//                                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
//                                    textAlign = TextAlign.Center
//                                )
//                            } else {
//                                Image(
//                                    bitmap = photoBitmap!!,
//                                    contentDescription = "Captured Image",
//                                    modifier = Modifier
//                                        .requiredSize(200.dp)
//                                        .clip(RoundedCornerShape(8.dp))
//                                )
//                            }
//                            Spacer(modifier = Modifier.height(16.dp))
//                            Button(onClick = {
//                                photoBitmap = null
//                                isCameraViewActive = false
//                            }) {
//                                Text("Open Camera")
//                            }
//                        }
//                    }
//                } else {
//                    CameraPermission(
//                        state = state,
//                        onRequestPermission = viewModel::requestPermission,
//                        openSettings = viewModel::openSettings,
//                        onDismiss = viewModel::onDismissDialog,
//                    )
//                }
//            }
//        }
//    }
}


@Composable
fun CameraPermission(
    modifier: Modifier = Modifier,
    state: CameraPermissionState,
    onRequestPermission: () -> Unit,
    openSettings: () -> Unit,
    onDismiss: () -> Unit,
) {

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "We need camera permission for this app to function",
                textAlign = TextAlign.Center,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
            Spacer(Modifier.size(8.dp))

            Button(
                onClick = { onRequestPermission() }
            ) {
                Text("Grant Permission")
            }

        }
    }

    when {
        state.isAlwaysDeniedDialogVisible -> AlwaysDeniedDialog(
            onOpenSettings = openSettings, onDismiss = onDismiss
        )
    }
}


@Composable
fun AlwaysDeniedDialog(
    onOpenSettings: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onOpenSettings()
                onDismiss()

            }) {
                Text("Open app settings")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text(text = "Camera permission required") },
        text = { Text("We need camera permission in order to use the camera") }
    )

}
