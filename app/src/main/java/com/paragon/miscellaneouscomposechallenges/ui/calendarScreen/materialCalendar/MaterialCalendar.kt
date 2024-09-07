package com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.materialCalendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.paragon.dvcore.utils.extensions.yyyy_MM_DD
import com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.calender.CalendarValidator
import com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.calender.DateValidator
import com.paragon.miscellaneouscomposechallneges.R
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialCalendar(
    modifier: Modifier = Modifier,
    dialogBackgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    requiredFormat: String = yyyy_MM_DD,
    lastSelectedDate: String = LocalDate.now().format(DateTimeFormatter.ofPattern(requiredFormat)),
    validator: DateValidator = DateValidator.UNSPECIFIED,
    datePickerColors: DatePickerColors = DatePickerDefaults.colors(),
    customDateRange: IntRange = IntRange(1900, 2050),
    onDateSelected: (String) -> Unit = {},
    onDialogDismiss: () -> Unit = {}
) {

    val date = LocalDate.parse(lastSelectedDate, DateTimeFormatter.ofPattern(requiredFormat))
    val initialSelectedDate = date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()

    val timePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDate,
        yearRange = customDateRange,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return CalendarValidator(validator).isValid(utcTimeMillis)
            }
        }
    )

    Dialog(
        properties = DialogProperties(
            decorFitsSystemWindows = false,
            usePlatformDefaultWidth = false
        ), onDismissRequest = { }) {
        Card(colors = CardDefaults.cardColors(containerColor = dialogBackgroundColor), modifier = modifier.fillMaxWidth()) {
            Column {
                DatePicker(
                    colors = datePickerColors.copy(containerColor = dialogBackgroundColor, dividerColor = Color.Transparent),
                    title = null,
                    headline = null,
                    showModeToggle = false,
                    state = timePickerState,
                )
                MaterialCalendarFooter(
                    datePickerColors = datePickerColors,
                    onCancelButtonClicked = onDialogDismiss,
                    onPositiveButtonClicked = {
                        timePickerState.selectedDateMillis?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                                ?.let { date ->
                                    onDateSelected(
                                        date.format(
                                            DateTimeFormatter.ofPattern(
                                                requiredFormat
                                            )
                                        )
                                    )
                                }
                        }
                    })
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialCalendarFooter(
    onCancelButtonClicked: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
    datePickerColors: DatePickerColors,
) {
    Row(
        horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        TextButton(onClick = onCancelButtonClicked) {
            Text(
                color = datePickerColors.titleContentColor,
                text = stringResource(id = R.string.label_cancel).uppercase(),
            )
        }
        TextButton(onClick = onPositiveButtonClicked) {
            Text(
                color = datePickerColors.titleContentColor,
                text = stringResource(id = R.string.label_ok).uppercase(),
            )
        }
    }
}
