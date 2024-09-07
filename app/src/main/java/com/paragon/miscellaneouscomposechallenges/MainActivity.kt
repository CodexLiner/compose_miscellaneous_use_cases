package com.paragon.miscellaneouscomposechallenges

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paragon.miscellaneouscomposechallenges.ui.blurBackrounds.BlurBackgroundScreen
import com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.CustomCalendarScreen
import com.paragon.miscellaneouscomposechallenges.ui.eventBusComposables.EventBus
import com.paragon.miscellaneouscomposechallenges.ui.eventBusComposables.EventBusOnComposableEvent
import com.paragon.miscellaneouscomposechallenges.ui.filePicker.FilePicker
import com.paragon.miscellaneouscomposechallenges.ui.home.HomeScreen
import com.paragon.miscellaneouscomposechallenges.ui.settings.NavigatingToTheSystemSettings
import com.paragon.miscellaneouscomposechallenges.ui.theme.MiscellaneousComposeChallengesTheme
import com.paragon.miscellaneouscomposechallenges.ui.timePicker.MaterialTimePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class NavigationItem(var route: String) {
    data object HomeScreen : NavigationItem("HomeScreen")
    data object CalendarScreen : NavigationItem("CalendarScreen")
    data object TimePickerScreen : NavigationItem("TimePickerScreen")
    data object FilePickerScreen : NavigationItem("FilePickerScreen")
    data object NavigatingToTheSystemSettings : NavigationItem("NavigatingToTheSystemSettings")
    data object BlurBackGroundScreen : NavigationItem("BlurBackGroundScreen")
    data object EventBusOnComposableEvent : NavigationItem("EventBusOnComposableEvent")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       CoroutineScope(Dispatchers.Main).launch {
           EventBus.current.collect {
               Log.e("TAG", "EventBusOnComposableEventScreen: onActivity  ${it.toString()}")
           }
       }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MiscellaneousComposeChallengesTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .safeDrawingPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = NavigationItem.HomeScreen.route
                        ) {
                            composable(NavigationItem.HomeScreen.route) {
                                HomeScreen {
                                    navController.navigate(it.route)
                                }
                            }
                            composable(NavigationItem.CalendarScreen.route) {
                                CustomCalendarScreen()
                            }
                            composable(NavigationItem.TimePickerScreen.route) {
                                MaterialTimePicker()
                            }
                            composable(NavigationItem.FilePickerScreen.route) {
                                FilePicker()
                            }
                            composable(NavigationItem.BlurBackGroundScreen.route) {
                                BlurBackgroundScreen()
                            }
                            composable(NavigationItem.EventBusOnComposableEvent.route) {
                                EventBusOnComposableEvent()
                            }
                            composable(NavigationItem.NavigatingToTheSystemSettings.route) {
                                NavigatingToTheSystemSettings()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiscellaneousComposeChallengesTheme {
        Greeting("Android")
    }
}