package com.jetbrains.kmpapp.screens.dropDown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.kmpapp.ToastManager
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownScreen(
    onBackClick: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Drop Down Screen") },
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
            CustomExposedDropdownMenu(
                modifier = Modifier
                    .widthIn(300.dp).padding(top = 16.dp)
                    .background(Color.White),
                exposedDropdownMenuModifier = Modifier.background(Color.White),
                items = listOf(
                    "India",
                    "America",
                    "Europe",
                    "Asia",
                    "Africa",
                    "Australia",
                    "Antarctica",
                    "Oceania",
                    "England"
                ),
                textFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = MaterialTheme.colorScheme.outline,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                ),
                showDivider = true,
                showCheckMark = true,
                borderColor = MaterialTheme.colorScheme.outline,
                dropdownMenuItemTextColor = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.small,
                dividerColor = MaterialTheme.colorScheme.outline,
                onItemSelected = { selectedItem ->
                    ToastManager().showToast("$selectedItem selected")
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CustomExposedDropdownMenu(
    modifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    items: List<T>,
    onItemSelected: (T) -> Unit,
    textFieldColors: TextFieldColors,
    borderColor: Color,
    dropdownMenuItemTextColor: Color,
    dividerColor: Color,
    shape: Shape,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    textStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal),
    placeholder: @Composable (() -> Unit)? = null,
    showDivider: Boolean = false,
    showCheckMark : Boolean = false
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(items.firstOrNull() ?: "") }

    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
        OutlinedTextField(
            modifier = modifier
                .menuAnchor()
                .exposedDropdownSize(matchTextFieldWidth = true),
            value = selectedItem.toString(),
            onValueChange = { newValue ->
                selectedItem = newValue
            },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "dropdown",
                    modifier = Modifier.rotate(if (isExpanded) 180f else 0f)
                )
            },
            singleLine = singleLine,
            maxLines = maxLines,
            textStyle = textStyle,
            placeholder = placeholder,
            shape = shape,
            colors = textFieldColors
        )

        ExposedDropdownMenu(
            modifier = exposedDropdownMenuModifier.border(BorderStroke(1.dp, borderColor)),
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedItem == item
                Column {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item.toString(),
                                color = dropdownMenuItemTextColor,
                                style = textStyle,
                                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                            )
                        },
                        onClick = {
                            selectedItem = item.toString()
                            onItemSelected(item)
                            isExpanded = false
                        },
                        trailingIcon = {
                            if (showCheckMark && isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "selected"
                                )
                            }
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                    if (showDivider && index != items.lastIndex) {
                        HorizontalDivider(color = dividerColor)
                    }
                }
            }
        }
    }
}