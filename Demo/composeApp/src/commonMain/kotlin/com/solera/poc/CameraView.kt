package com.jetbrains.kmpapp

import androidx.compose.runtime.Composable

@Composable
expect fun CameraView(result: (Any?) -> Unit)