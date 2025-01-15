package com.jetbrains.kmpapp.screens.uiElements


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.back
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiElementsScreen(
    onBackClick: () -> Unit
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var isDialogVisible by remember { mutableStateOf(false) }
    var isLoadingVisible by remember { mutableStateOf(false) }
    var isProgressVisible by remember { mutableStateOf(false) }
    var progressValue by remember { mutableStateOf(0.0f) }
    var textFieldValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current


    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UI Elements Screen") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors()
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Button(onClick = { isBottomSheetVisible = true }) {
                    Text("Open Bottom Sheet")
                }

                Button(onClick = { isDialogVisible = true }) {
                    Text("Show Alert Dialog")
                }

                Button(onClick = {
                    isLoadingVisible = true
                    coroutineScope.launch {
                        delay(2000)
                        isLoadingVisible = false
                    }
                }) {
                    Text("Show Loading")
                }

                if (isLoadingVisible) {
                    CircularProgressIndicator()
                }

                Button(onClick = {
                    isProgressVisible = !isProgressVisible
                    if (isProgressVisible) {
                        coroutineScope.launch {
                            for (i in 1..100) {
                                progressValue = i / 100.0f
                                delay(50)
                            }
                        }
                    }
                }) {
                    Text("Toggle Progress Bar")
                }

                if (isProgressVisible) {
                    LinearProgressIndicator(
                        progress = {progressValue},
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                OutlinedTextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = { Text("Enter text") },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    }),
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Entered Text: $textFieldValue")
            }
        }
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetVisible = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            BottomSheetContent(onClose = { isBottomSheetVisible = false })
        }
    }

    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = { isDialogVisible = false },
            confirmButton = {
                TextButton(onClick = { isDialogVisible = false }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { isDialogVisible = false }) {
                    Text("Dismiss")
                }
            },
            title = { Text("Alert Dialog") },
            text = { Text("This is an example of an Alert Dialog.") }
        )
    }
}

@Composable
fun BottomSheetContent(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "This is a Bottom Sheet",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClose,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Close")
        }
    }
}






