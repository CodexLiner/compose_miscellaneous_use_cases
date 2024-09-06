package com.paragon.miscellaneouscomposechallenges.ui.calendarScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.paragon.dvcore.utils.extensions.yyyy_MM_DD
import com.paragon.miscellaneouscomposechallenges.ui.calender.CustomCalendar
import com.paragon.miscellaneouscomposechallenges.ui.calender.DateValidator
import com.paragon.miscellaneouscomposechallenges.ui.materialCalendar.MaterialCalendar
import com.paragon.miscellaneouscomposechallenges.ui.theme.CustomCalendarTheme
import com.paragon.miscellaneouscomposechallneges.R
import com.paragon.riyadhair.ui.compose.ui.components.calender.dropShadow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCalendarScreen() {
    val showCustomCalendar = remember { mutableStateOf(false) }
    val showMaterialCalendar = remember { mutableStateOf(false) }
    var currentCalenderDateState by rememberSaveable {
        mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern(yyyy_MM_DD)))
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedCard(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.labelLarge,
                text =currentCalenderDateState.toString()
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = {
               showCustomCalendar.value = true
            }) {
                Text("Open Custom Date Picker")
            }
            Button(onClick = {
                showMaterialCalendar.value = true
            }) {
                Text("Open Material Date Picker")
            }
        }
    }
    if (showCustomCalendar.value){
        CustomCalendar(
            lastSelectedDate = currentCalenderDateState,
            requiredFormat = yyyy_MM_DD,
            validator = DateValidator.THIS_WEEK,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen._10dp))
                .dropShadow(
                    RoundedCornerShape(dimensionResource(id = R.dimen._20dp)),
                    Color.Black.copy(0.05f),
                    0.dp,
                    offsetY = 1.dp
                )
                .dropShadow(
                    RoundedCornerShape(dimensionResource(id = R.dimen._20dp)),
                    Color.Black.copy(0.10f),
                    blur = 4.dp,
                    offsetY = 4.dp
                )
                .dropShadow(
                    RoundedCornerShape(dimensionResource(id = R.dimen._20dp)),
                    Color.Black.copy(0.12f),
                    10.dp,
                    offsetY = 10.dp
                )
                .background(
                    color = colorResource(id = R.color.colorPrimary),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._20dp))
                ),
            onDateSelected = { date ->
                currentCalenderDateState = date
                showCustomCalendar.value = !showCustomCalendar.value
            },
            onDialogDismiss = {showCustomCalendar.value = showCustomCalendar.value.not()}
        )
    }

    if (showMaterialCalendar.value){
        CustomCalendarTheme {
            MaterialCalendar(
                lastSelectedDate = currentCalenderDateState,
                requiredFormat = yyyy_MM_DD,
                validator = DateValidator.NEXT_THREE_MONTHS,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen._10dp))
                    .dropShadow(
                        RoundedCornerShape(dimensionResource(id = R.dimen._20dp)),
                        Color.Black.copy(0.05f),
                        0.dp,
                        offsetY = 1.dp
                    )
                    .dropShadow(
                        RoundedCornerShape(dimensionResource(id = R.dimen._20dp)),
                        Color.Black.copy(0.10f),
                        blur = 4.dp,
                        offsetY = 4.dp
                    )
                    .dropShadow(
                        RoundedCornerShape(dimensionResource(id = R.dimen._20dp)),
                        Color.Black.copy(0.12f),
                        10.dp,
                        offsetY = 10.dp
                    )
                    .background(
                        color = colorResource(id = R.color.colorPrimary),
                        shape = RoundedCornerShape(dimensionResource(id = R.dimen._20dp))
                    ),
                onDateSelected = { date ->
                    currentCalenderDateState = date
                    showMaterialCalendar.value = !showMaterialCalendar.value
                },
                onDialogDismiss = {showMaterialCalendar.value = showMaterialCalendar.value.not()}
            )
        }

    }

}