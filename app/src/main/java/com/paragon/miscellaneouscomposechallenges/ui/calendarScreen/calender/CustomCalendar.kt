package com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.calender

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.paragon.dvcore.utils.extensions.yyyy_MM_DD
import com.paragon.miscellaneouscomposechallneges.R
import com.paragon.riyadhair.ui.compose.ui.components.calender.CalendarDataSource
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Preview(showSystemUi = true)
@Composable
fun CalendarAppPreview() {
    CustomCalendar(modifier = Modifier.padding(dimensionResource(id = R.dimen._16dp)))
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomCalendar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = colorResource(id = R.color.colorPrimary),
    requiredFormat: String = yyyy_MM_DD,
    lastSelectedDate: String = LocalDate.now().format(DateTimeFormatter.ofPattern(requiredFormat)),
    validator: DateValidator = DateValidator.UNSPECIFIED,
    /** Please Pass Date in this format "yyyy_MM_DD" in case of custom date */
    customDateRange: Pair<String, String> = Pair("1970-01-01", "2100-01-01"),
    onDateSelected: (String) -> Unit = {},
    onDialogDismiss: () -> Unit = {}
) {
    val dataSource = CalendarDataSource()
    val lastSelectedDateFormatter = remember { DateTimeFormatter.ofPattern(requiredFormat) }

    ValidationHelper(customDateRange).invoke(validator) { minDate, maxDate ->
        if (minDate.time > -1)
            dataSource.minDate = minDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        if (maxDate.time > -1)
            dataSource.maxDate = maxDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }


    /** logic for showing calendar with previously selected date */
    val selectedDate = remember { mutableStateOf(LocalDate.parse(lastSelectedDate, lastSelectedDateFormatter)) }
    var data by remember { mutableStateOf(dataSource.getData(lastSelectedDate = selectedDate.value ?: dataSource.today  , startDate = selectedDate.value)) }
    var lastMonth by remember { mutableStateOf(dataSource.getData(lastSelectedDate = selectedDate.value , startDate = selectedDate.value.minusMonths(1) ?: dataSource.today)) }
    var nextMonth by remember { mutableStateOf(dataSource.getData(lastSelectedDate = selectedDate.value , startDate = selectedDate.value.plusMonths(1) ?: dataSource.today)) }

    val haptic = LocalHapticFeedback.current

    /*view pager state*/
    val pagerState = rememberPagerState(initialPage = data.selectedDate.date.month.value - 1)
    val coroutineScope = rememberCoroutineScope()
    var previousPage by remember { mutableIntStateOf(pagerState.currentPage) }

    /** Define the formatter */
    val formatter = remember { DateTimeFormatter.ofPattern(requiredFormat) }
        Dialog(
            properties = DialogProperties(
                decorFitsSystemWindows = false,
                usePlatformDefaultWidth = false
            ), onDismissRequest = { }) {
            Card(
                modifier = modifier
                    .padding(dimensionResource(id = R.dimen._16dp))
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = backgroundColor , contentColor = backgroundColor , disabledContainerColor = backgroundColor)
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Header(data = data, onPrevClickListener = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage - 1) }
                    }, onNextClickListener = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    })
                    Content(data = data, nextMonth= nextMonth , lastMonth =  lastMonth, dataSource = dataSource, pagerState = pagerState) { date ->
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)

                        data = data.copy(selectedDate = date, visibleDates = data.visibleDates.map { it.copy(isSelected = it.date.isEqual(date.date)) })
                        nextMonth = nextMonth.copy(selectedDate = date, visibleDates = nextMonth.visibleDates.map { it.copy(isSelected = it.date.isEqual(date.date)) })
                        lastMonth = lastMonth.copy(selectedDate = date, visibleDates = lastMonth.visibleDates.map { it.copy(isSelected = it.date.isEqual(date.date)) })

                    }
                    Footer(
                        onCancelButtonClicked = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onDialogDismiss()
                        },
                        onPositiveButtonClicked = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onDateSelected(data.selectedDate.date.format(formatter))
                        })
                }
                /** view pager state snapShot Listener */
                LaunchedEffect(pagerState) {
                    snapshotFlow { pagerState.currentPage }.collect { page ->
                        if (page > previousPage) {
                            lastMonth = data
                            data = nextMonth
                            nextMonth  = dataSource.getData(startDate = data.visibleDates.first().date.plusMonths(1), lastSelectedDate = selectedDate.value)

                        } else if (page < previousPage) {
                            nextMonth = data
                            data = lastMonth
                            lastMonth  = dataSource.getData(startDate = data.visibleDates.first().date.minusMonths(1), lastSelectedDate = selectedDate.value)
                        }

                        previousPage = page
                        if (page == 0) {
                            coroutineScope.launch {
                                pagerState.scrollToPage((data.selectedDate.date.month.value - 1))
                            }
                        } else if (page == Int.MAX_VALUE - 1) {
                            coroutineScope.launch {
                                pagerState.scrollToPage(1)
                            }
                        }
                    }
                }
            }
        }

}

fun swipeCalendar(
    haptic: HapticFeedback,
    dataSource: CalendarDataSource,
    finalStartDate: LocalDate,
    lastSelectedDate: LocalDate
): CalendarUiModel {
    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
    var data: CalendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = lastSelectedDate)
    data = data.copy(selectedDate = data.selectedDate.copy(date = finalStartDate))
    return data
}


@Composable
fun Header(
    data: CalendarUiModel,
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    Row {
        Text(
            color = colorResource(id = R.color.white),
            text = data.visibleDates.firstOrNull()?.date?.format(DateTimeFormatter.ofPattern("MMMM yyyy"))?.uppercase().orEmpty(),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = { onPrevClickListener(data.startDate.date) }) {
            Icon(tint = colorResource(id = R.color.white), imageVector = Icons.Filled.ChevronLeft, contentDescription = "Back")
        }
        IconButton(onClick = { onNextClickListener(data.endDate.date) }) {
            Icon(tint = colorResource(id = R.color.white), imageVector = Icons.Filled.ChevronRight, contentDescription = "Next")
        }
    }
}

@Composable
fun Footer(
    onCancelButtonClicked: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
) {
    Row(horizontalArrangement = Arrangement.End , modifier = Modifier
        .fillMaxWidth()
        .padding(top = dimensionResource(id = R.dimen._32dp))) {
        val modifier = Modifier.padding(start = dimensionResource(id = R.dimen._32dp))
        Text(
            color = colorResource(id = R.color.white),
            text = stringResource(id = R.string.label_cancel).uppercase(),
            modifier = modifier.clickable { onCancelButtonClicked() }
        )
        Text(
            color = colorResource(id = R.color.white),
            text = stringResource(id = R.string.label_ok).uppercase(),
            modifier = modifier.clickable { onPositiveButtonClicked() }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Content(
    data: CalendarUiModel,
    lastMonth: CalendarUiModel,
    nextMonth: CalendarUiModel,
    dataSource: CalendarDataSource,
    pagerState: PagerState,
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
    WeekDays(dataSource = dataSource)
    HorizontalPager(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen._260dp))
            .fillMaxWidth(),
        count = Int.MAX_VALUE,
        verticalAlignment = Alignment.Top,
        state = pagerState,
    ) { page ->
        val currentData = when {
            page == pagerState.currentPage - 1 -> lastMonth
            page == pagerState.currentPage + 1 -> nextMonth
            else -> data
        }
        LazyVerticalGrid(
            modifier = Modifier.defaultMinSize(minHeight = dimensionResource(id = R.dimen._260dp)),
            columns = GridCells.Fixed(7),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._10dp)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._8dp))
        ) {
            val dates = dataSource.getModifiedDates(currentData.visibleDates)
            items(dates.size) { index ->
                if (dates[index].fake.not()) {
                    ContentItem(
                        date = dates[index], onDateClickListener
                    )
                }
            }
        }
    }
}

@Composable
fun WeekDays(dataSource: CalendarDataSource) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._10dp)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._8dp))
    ){
        items(dataSource.getWeekDays().size) {
            Column(modifier = Modifier.padding(top = dimensionResource(id = R.dimen._16dp))) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = colorResource(id = R.color.white),
                    text = dataSource.getWeekDays()[it].toString().first().uppercase(),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen._20dp)))
            }
        }
    }
}

@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit,
) {
        val interactionSource = remember { MutableInteractionSource() }
    OutlinedCard(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen._100dp)),
        border = BorderStroke(
            dimensionResource(id = R.dimen._1dp),
            color = if (date.isToday && date.isSelected.not()) colorResource(id = R.color.white)
            else if (date.isSelected && date.isToday.not()) colorResource(id = R.color.color_transparent)
            else  colorResource(id = R.color.color_transparent)
        ),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen._35dp))
            .width(dimensionResource(id = R.dimen._35dp))
            .clickable(enabled = true,
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (date.isValidDate) {
                        onClickListener(date)
                    }
                }),
        colors = CardDefaults.cardColors(
            containerColor = if (date.isSelected) colorResource(id = R.color.color_soft_sunrise) else colorResource(id = R.color.color_transparent)
        ),
    ) {
        Column(verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Text(
                color = if (date.isValidDate) colorResource(id = R.color.white) else colorResource(id = R.color.grey),
                text = date.date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

