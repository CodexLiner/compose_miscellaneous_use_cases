package com.paragon.miscellaneouscomposechallenges.ui.timePicker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTimePicker(
    modifier: Modifier = Modifier,
    dialogBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
) {
    val timePickerState = rememberTimePickerState(is24Hour = false)
    Dialog(
        properties = DialogProperties(), onDismissRequest = { }) {
        Card(
            colors = CardDefaults.cardColors(containerColor = dialogBackgroundColor),
            modifier = modifier.fillMaxWidth()
        ) {
            val blueTimePickerColors = TimePickerColors(
                clockDialColor = Color.Blue,
                selectorColor = Color.Blue,
                containerColor = Color.Blue,
                periodSelectorBorderColor = Color.Blue,
                clockDialSelectedContentColor = Color.Blue,clockDialUnselectedContentColor = Color.Blue,
                periodSelectorSelectedContainerColor = Color.Blue,
                periodSelectorUnselectedContainerColor = Color.Blue,
                periodSelectorSelectedContentColor = Color.Blue,
                periodSelectorUnselectedContentColor = Color.Blue,
                timeSelectorSelectedContainerColor = Color.Blue,
                timeSelectorUnselectedContainerColor = Color.Blue,
                timeSelectorSelectedContentColor = Color.Blue,
                timeSelectorUnselectedContentColor = Color.Blue
            )
            Column {
                TimePicker(
                    colors =  TimePickerDefaults.colors().copy(timeSelectorSelectedContainerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp),
                    layoutType = TimePickerLayoutType.Vertical,
                    state = timePickerState
                )
            }
        }
    }


}