package com.paragon.miscellaneouscomposechallenges.ui.eventBusComposables

import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * this is a generic event class for composable
 * please @see [LocalEvent] and its usage in @see [MiscellaneousComposeChallengesTheme]
 */
object EventBus {
    private val _events = MutableSharedFlow<Any>()
    val current = _events.asSharedFlow()
    fun <T : Any> sendEvent(event: T) {
        CoroutineScope(Dispatchers.Main).launch {
            _events.emit(event)
        }
    }
}

val LocalEvent = staticCompositionLocalOf {
    EventBus.current
}
