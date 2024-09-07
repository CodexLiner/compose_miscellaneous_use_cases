package com.paragon.miscellaneouscomposechallenges.ui.eventBusComposables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class EventNavigationItem(val route: String) {
    data object EventBusOnComposableEventScreen1 : EventNavigationItem("EventBusOnComposableEvent1")
    data object EventBusOnComposableEventScreen2 : EventNavigationItem("EventBusOnComposableEvent2")
    data object EventBusOnComposableEventScreen3 : EventNavigationItem("EventBusOnComposableEvent3")
    data object EventBusOnComposableEventScreen4 : EventNavigationItem("EventBusOnComposableEvent4")
    data object EventBusOnComposableEventScreen5 : EventNavigationItem("EventBusOnComposableEvent5")
    data object EventBusOnComposableEventScreen6 : EventNavigationItem("EventBusOnComposableEvent6")
    data object EventBusOnComposableEventScreen7 : EventNavigationItem("EventBusOnComposableEvent7")
    data object EventBusOnComposableEventScreen8 : EventNavigationItem("EventBusOnComposableEvent8")
    data object EventBusOnComposableEventScreen9 : EventNavigationItem("EventBusOnComposableEvent9")
    data object EventBusOnComposableEventScreen10 : EventNavigationItem("EventBusOnComposableEvent10")
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventBusOnComposableEvent() {
    val navController = rememberNavController()
    Box(contentAlignment = Alignment.Center) {
        NavHost(
            startDestination = EventNavigationItem.EventBusOnComposableEventScreen1.route,
            navController = navController
        ) {
            composable(EventNavigationItem.EventBusOnComposableEventScreen1.route) {
                EventBusOnComposableEventScreen1{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen2.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen2.route) {
                EventBusOnComposableEventScreen2{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen3.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen3.route) {
                EventBusOnComposableEventScreen3{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen4.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen4.route) {
                EventBusOnComposableEventScreen4{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen5.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen5.route) {
                EventBusOnComposableEventScreen5{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen6.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen6.route) {
                EventBusOnComposableEventScreen6{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen7.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen7.route) {
                EventBusOnComposableEventScreen7{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen8.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen8.route) {
                EventBusOnComposableEventScreen8{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen9.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen9.route) {
                EventBusOnComposableEventScreen9{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen10.route)
                }
            }
            composable(EventNavigationItem.EventBusOnComposableEventScreen10.route) {
                EventBusOnComposableEventScreen10{
                    navController.navigate(EventNavigationItem.EventBusOnComposableEventScreen1.route)
                }
            }
        }
    }
}

@Composable
fun EventBusSender() {
    Button(onClick = {
        EventBus.sendEvent(getRandomString(10))
    }) {
        Text("Send Random Text Event")
    }
}

@Composable
fun EventBusSubScribed() {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        event.collect {
            eventState = it.toString()
        }
    }
    OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
}

fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length).map { allowedChars.random() }.joinToString("")
}