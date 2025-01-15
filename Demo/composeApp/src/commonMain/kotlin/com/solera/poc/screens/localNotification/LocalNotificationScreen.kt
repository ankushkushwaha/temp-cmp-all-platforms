package com.jetbrains.kmpapp.screens.localNotification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetbrains.kmpapp.NotificationManager
//import dev.icerock.moko.permissions.PermissionState
//import dev.icerock.moko.permissions.compose.BindEffect
//import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.back
import demo.composeapp.generated.resources.local_notification_screen
import demo.composeapp.generated.resources.open_app_settings
import demo.composeapp.generated.resources.permission_declined
import demo.composeapp.generated.resources.permission_granted
import demo.composeapp.generated.resources.request_permission
import demo.composeapp.generated.resources.send_notification
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalNotificationScreen(
    onBackClick: () -> Unit,
) {

////    val factory = rememberPermissionsControllerFactory()
////    val controller = remember(factory) {
////        factory.createPermissionsController()
////    }
////
////    BindEffect(controller)
//
//    val viewModel = viewModel {
//        PermissionsViewModel(
//            controller,
//        )
//    }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    // Observe lifecycle to refresh permission state when returning from settings
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_RESUME) {
//                viewModel.loadPermissionState() // Custom method to refresh the state
//            }
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(stringResource(Res.string.local_notification_screen)) },
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
//                .padding(paddingValues),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                when (viewModel.state) {
//                    PermissionState.Granted -> {
//                        Text(stringResource(Res.string.permission_granted))
//                    }
//                    PermissionState.DeniedAlways,PermissionState.NotGranted -> {
//                        Text(stringResource(Res.string.permission_declined))
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Button(onClick = {
//                            controller.openAppSettings()
//                        }) {
//                            Text(stringResource(Res.string.open_app_settings))
//                        }
//                    }
//
//                    else -> {
//                        Button(
//                            onClick = {
//                                viewModel.provideOrRequestRecordAudioPermission()
//                            }
//                        ) {
//                            Text(stringResource(Res.string.request_permission))
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                Button(
//                    onClick = {
//                        NotificationManager().showNotification(
//                            title = "Local Notification",
//                            description = "This is a local notification"
//                        )
//                    }
//                ) {
//                    Text(stringResource(Res.string.send_notification))
//                }
//            }
//        }
//    }
}