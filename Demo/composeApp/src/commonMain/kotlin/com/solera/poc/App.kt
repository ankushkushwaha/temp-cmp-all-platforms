package com.jetbrains.kmpapp

//import DatePickerSample
import RefreshListScreen
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jetbrains.kmpapp.screens.dropDown.DropDownScreen
import com.jetbrains.kmpapp.screens.Location.CurrentLocationScreen
import com.jetbrains.kmpapp.screens.camera.CameraPermissionScreen
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.list.ListScreen
import com.jetbrains.kmpapp.screens.localNotification.LocalNotificationScreen
import com.jetbrains.kmpapp.screens.menu.DestinationScreen
import com.jetbrains.kmpapp.screens.menu.MenuList
import com.jetbrains.kmpapp.screens.menu.MenuListScreen
import com.jetbrains.kmpapp.screens.uiElements.GestureEnabledModalNavigationDrawer
import com.jetbrains.kmpapp.screens.uiElements.UiElementsScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Serializable
object MenuDestination

@Serializable
object ListDestination

@Serializable
data class DetailDestination(val objectId: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            val navController: NavHostController = rememberNavController()
            NavHost(navController = navController, startDestination = MenuDestination,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Start,
                        tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.End,
                        tween(700)
                    )
                }
            ) {
                composable<MenuDestination> {
                    MenuListScreen(
                        navigateToNextScreen = { objectId ->
                            navController.navigate(getNextScreen(objectId))
                        }
                    )
                }
                composable<DestinationScreen.GridListScreen> {
                    ListScreen(navigateToDetails = { objectId ->
                        navController.navigate(DetailDestination(objectId))
                    },
                        navigateBack = {
                            navController.popBackStack()
                        })
                }
                composable<DestinationScreen.RefreshListScreen> {
                    RefreshListScreen {
                        navController.popBackStack()
                    }
                }

                composable<DetailDestination> { backStackEntry ->
                    DetailScreen(
                        objectId = backStackEntry.toRoute<DetailDestination>().objectId,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
                composable<DestinationScreen.MapScreen> {
                    LaunchedEffect(Unit) {
                        ToastManager().showToast("Integrate Here Map and show Map screen")
                        delay(3000)
                        navController.popBackStack()
                    }
                }
                composable<DestinationScreen.UiElementsScreen> {
                    UiElementsScreen(
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
                composable<DestinationScreen.NavigationDrawer> {
                    GestureEnabledModalNavigationDrawer( onBackClick =
                        {
                            navController.popBackStack()
                        }
                    )
                }
                composable<DestinationScreen.CameraScreen> {
                    CameraPermissionScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
                }
                composable<DestinationScreen.DatePickerScreen> {
//                    DatePickerSample(
//                        onBackClick = {
//                            navController.popBackStack()
//                        }
//                    )
                }
                composable<DestinationScreen.DropDownScreen> {
                    DropDownScreen(
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
                composable<DestinationScreen.FetchLocation> {
                    CurrentLocationScreen(
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
                composable<DestinationScreen.LocalNotification> {
                    LocalNotificationScreen(
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

fun getNextScreen(objectId: Int): Any {
    return when (objectId) {
        MenuList.GRID_LIST.ordinal -> DestinationScreen.GridListScreen
        MenuList.REFRESH_LIST.ordinal -> DestinationScreen.RefreshListScreen
        MenuList.MAP.ordinal -> DestinationScreen.MapScreen
        MenuList.UI_ELEMENTS.ordinal -> DestinationScreen.UiElementsScreen
        MenuList.CAMERA.ordinal -> DestinationScreen.CameraScreen
        MenuList.DROP_DOWN.ordinal -> DestinationScreen.DropDownScreen
        MenuList.DATE_PICKER.ordinal -> DestinationScreen.DatePickerScreen
        MenuList.FETCH_LOCATION.ordinal -> DestinationScreen.FetchLocation
        MenuList.LOCAL_NOTIFICATION.ordinal -> DestinationScreen.LocalNotification
        MenuList.NAVIGATION_DRAWER.ordinal -> DestinationScreen.NavigationDrawer
        else -> {}
    }
}
