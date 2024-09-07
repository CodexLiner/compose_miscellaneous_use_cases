package com.paragon.miscellaneouscomposechallenges.ui.eventBusComposables

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.paragon.miscellaneouscomposechallenges.ui.lifecycle.OnLifecycleEvent

@Composable
fun EventBusOnComposableEventScreen1(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 1")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 1: $it")
            eventState = "$it on Screen 1"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 1: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen2(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 2")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 2: $it")
            eventState = "$it on Screen 2"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 2: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen3(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 3")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 3: $it")
            eventState = "$it on Screen 3"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 3: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen4(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 4")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 4: $it")
            eventState = "$it on Screen 4"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 4: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen5(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 5")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 5: $it")
            eventState = "$it on Screen 5"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreen Screen 5: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen6(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 6")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 6: $it")
            eventState = "$it on Screen 6"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 10: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen7(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 7")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 7: $it")
            eventState = "$it on Screen 7"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 7: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen8(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 8")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 8: $it")
            eventState = "$it on Screen 8"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 8: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}


@Composable
fun EventBusOnComposableEventScreen9(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 9")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 9: $it")
            eventState = "$it on Screen 9"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 9: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}

@Composable
fun EventBusOnComposableEventScreen10(onClickListener: () -> Unit = {}) {
    val event = LocalEvent.current
    var eventState by remember {
        mutableStateOf("Screen 10")
    }
    LaunchedEffect(Unit) {
        event.collect {
            Log.e("TAG", "EventBusOnComposableEventScreen Screen 10: $it")
            eventState = "$it on Screen 10"
        }
    }
    OnLifecycleEvent { _, event ->
        Log.e("TAG", "EventBusOnComposableEventScreenLifeCycle Screen 10: $event")
    }

    Column {
        Button(onClick = {
            onClickListener()
        }) { Text("Navigate to Home") }

        OutlinedTextField(value = eventState.toString(), onValueChange = {}, readOnly = true)
        Button(onClick = {
            EventBus.sendEvent(getRandomString(10))
        }) {
            Text("Send Random Text Event")
        }
    }
}
