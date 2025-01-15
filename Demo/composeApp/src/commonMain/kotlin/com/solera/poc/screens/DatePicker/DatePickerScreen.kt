//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import kmp_app_template.composeapp.generated.resources.Res
//import kmp_app_template.composeapp.generated.resources.back
//import kotlinx.datetime.Clock
//import kotlinx.datetime.Instant
//import kotlinx.datetime.TimeZone
//import kotlinx.datetime.toLocalDateTime
//import org.jetbrains.compose.resources.stringResource
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DatePickerSample(
//    onBackClick: () -> Unit,
//) {
//    var showDatePicker by remember { mutableStateOf(false) } // Controls visibility of date picker
//    var selectedDate by remember { mutableStateOf<Long?>(null) } // Stores the selected date in milliseconds
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Date Picker Screen") },
//                navigationIcon = {
//                    IconButton(onClick = onBackClick) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
//                    }
//                },
//            )
//        }
//    ) { paddingValues ->
//        LazyColumn(
//            modifier = Modifier
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//            item {
//                // Format the selected date
//                val formattedDate = remember(selectedDate) {
//                    selectedDate?.let {
//                        val instant = Instant.fromEpochMilliseconds(it)
//                        val localDate =
//                            instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
//                        "${
//                            localDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
//                        } ${localDate.dayOfMonth}, ${localDate.year}"
//                    } ?: Clock.System.now().toEpochMilliseconds().let {
//                        val instant = Instant.fromEpochMilliseconds(it)
//                        val localDate =
//                            instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
//                        "${
//                            localDate.month.name.lowercase().replaceFirstChar { it.uppercase() }
//                        } ${localDate.dayOfMonth}, ${localDate.year}"
//                    }
//                }
//
//                // Column with text and calendar button
//                Column(
//                    modifier = Modifier
//                        .padding(paddingValues)
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    // Display formatted date
//                    Text(
//                        text = "Selected date: $formattedDate",
//                        modifier = Modifier.fillMaxWidth() // Ensure Text takes full width
//                    )
//                    // Button to open date picker
//                    TextButton(onClick = { showDatePicker = true }) {
//                        Text("Open Calendar")
//                    }
//                }
//            }
//        }
//
//        // Show the date picker dialog when triggered
//        if (showDatePicker) {
//
//            DatePickerComponent(
//                initialDateMillis = selectedDate ?: Clock.System.now().toEpochMilliseconds(),
//                onDateSelected = { selectedDate = it },
//                onDismiss = { showDatePicker = false }
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DatePickerComponent(
//    initialDateMillis: Long,
//    onDateSelected: (Long) -> Unit,
//    onDismiss: () -> Unit
//) {
//    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)
//    DatePickerDialog(
//        onDismissRequest = { onDismiss() },
//        confirmButton = {
//            Button(
//                onClick = {
//                    onDateSelected(datePickerState.selectedDateMillis ?: Clock.System.now().toEpochMilliseconds())
//                    onDismiss()
//                },
//                modifier = Modifier
//                    .padding(4.dp)
//            ) {
//                Text(
//                    text = "OK",
//                    color = Color.White
//                )
//            }}, dismissButton = {
//            Button(
//                onClick = {
//                    onDismiss()
//                },
//                modifier = Modifier
//                    .padding(4.dp)
//            ) {
//                Text("Cancel")
//            } },
//        tonalElevation = 0.dp,
//        colors = DatePickerDefaults.colors(
//            containerColor = MaterialTheme.colorScheme.surface
//        )
//    ) {
//        DatePicker(
//            modifier = Modifier
//                .wrapContentHeight(),
//            state = datePickerState,
//            colors = DatePickerDefaults.colors(
//                containerColor = MaterialTheme.colorScheme.surface
//            )
//        )
//    }
//}