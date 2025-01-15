package com.jetbrains.kmpapp.screens.Location

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jetbrains.kmpapp.Utils.LocationManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
//import dev.icerock.moko.permissions.compose.BindEffect
//import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.rememberCoroutineScope
//import dev.icerock.moko.permissions.PermissionState
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.back
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentLocationScreen(
    onBackClick: () -> Unit,
    locationManager: LocationManager = LocationManager()
) {
//    val factory = rememberPermissionsControllerFactory()
//    val controller = remember(factory) {
//        factory.createPermissionsController()
//    }
//    val coroutineScope = rememberCoroutineScope()
//
//    BindEffect(controller)
//
//    val viewModel = viewModel {
//        PermissionsViewModel(controller)
//    }
//    var location: Pair<Double, Double>? by remember { mutableStateOf(null) }
//    var isLoading by remember { mutableStateOf(false) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Current Location Screen") },
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
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                when (viewModel.state) {
//                    PermissionState.Granted -> {
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Button(
//                            onClick = {
//                                coroutineScope.launch {
//                                    isLoading = true
//                                    val locationResult = locationManager.requestCurrentLocation()
//                                    if (locationResult.isSuccess) {
//                                        locationResult.getOrNull()?.let {
//                                            location = Pair(it.latitude, it.longitude)
//                                        }
//                                    } else {
//                                        println("Error: ${locationResult.exceptionOrNull()?.message}")
//                                    }
//                                    isLoading = false
//                                }
//                            },
//                            enabled = !isLoading
//                        ) {
//                            if (isLoading) {
//                                CircularProgressIndicator(
//                                    modifier = Modifier.size(24.dp),
//                                    color = MaterialTheme.colorScheme.primary,
//                                    strokeWidth = 2.dp
//                                )
//                            } else {
//                                if (location != null) {
//                                    Text("Latitude ${location!!.first}, Longitude: ${location!!.second}")
//                                } else {
//                                    Text("Get Current Location")
//                                }
//                            }
//                        }
//                    }
//                    PermissionState.DeniedAlways -> {
//                        Text("Permission was not granted.")
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Button(onClick = { controller.openAppSettings() }) {
//                            Text("Open app settings")
//                        }
//                    }
//                    else -> {
//                        Button(onClick = { viewModel.provideOrRequestRecordAudioPermission() }) {
//                            Text("Request permission")
//                        }
//                    }
//                }
//            }
//        }
//    }
}