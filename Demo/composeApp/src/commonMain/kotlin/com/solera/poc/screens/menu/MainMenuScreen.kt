package com.jetbrains.kmpapp.screens.menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable

@Composable
fun MenuListScreen(
    navigateToNextScreen: (objectId: Int) -> Unit
) {

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            (TopAppBar(
        title = { Text(text = "Select Component",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        ) },
    ))
        },
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars).padding(bottom = 40.dp),
    ) { paddingValues ->
        AnimatedContent(MenuList.entries.toTypedArray()) { objectsAvailable ->
            MenuList(
                items = MenuList.entries.toTypedArray(),
                onObjectClick = navigateToNextScreen,
                paddingValues = paddingValues
            )
        }
    }

}

@Composable
private fun MenuList(
    items: Array<MenuList>,
    onObjectClick: (Int) -> Unit,
    paddingValues: PaddingValues,
) {
    items.sort()
    LazyColumn (
        modifier = Modifier.fillMaxSize().padding(start = 28.dp),
        contentPadding = paddingValues
    ) {
        items(items) { item ->
            TextButton(
                onClick = { onObjectClick(item.ordinal) }
            ) {
                Row(Modifier.padding(8.dp),verticalAlignment = Alignment.CenterVertically) {
                    Canvas(modifier = Modifier.padding(start = 8.dp,end = 16.dp).size(6.dp)){
                        drawCircle(Color.Black)
                    }
                    Text( item.itemName/*.lowercase().capitalize(Locale.current)*/,
                        fontSize = 20.sp,
                        color = Color.Black)
                }
            }
        }
    }
}

enum class MenuList(val itemName: String) {
    UI_ELEMENTS("UI Elements"),
    DATE_PICKER("Date Picker"),
    DROP_DOWN("Dropdown"),
    REFRESH_LIST("Swipe to Refresh"),
    NAVIGATION_DRAWER("Navigation Drawer"),
    GRID_LIST("Grid List"),
    LOCAL_NOTIFICATION("Local Notification"),
    FETCH_LOCATION("Fetch Location"),
    CAMERA("Camera"),
    MAP("Map"),


}

@Serializable
sealed class DestinationScreen {
    @Serializable
    object MapScreen
    @Serializable
    object GridListScreen
    @Serializable
    object RefreshListScreen
    @Serializable
    object UiElementsScreen
    @Serializable
    object CameraScreen
    @Serializable
    object FetchLocation
    @Serializable
    object DatePickerScreen
    @Serializable
    object DropDownScreen
    @Serializable
    object LocalNotification
    @Serializable
    object NavigationDrawer
}
