package com.paragon.riyadhair.ui.compose.ui.components.calender

import com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.calender.CalendarUiModel
import com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.calender.CalendarUiModel.Date
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters
import java.util.stream.Collectors
import java.util.stream.Stream


class CalendarDataSource {

    val today: LocalDate
        get() {
            return LocalDate.now()
        }
    var minDate: LocalDate = LocalDate.of(1970, 1, 1)
    var maxDate: LocalDate = LocalDate.of(2100, 1, 1)

    fun getData(startDate: LocalDate = today, lastSelectedDate: LocalDate): CalendarUiModel {
        val firstDayOfWeek = startDate.withDayOfMonth(1)
        val endDayOfWeek = firstDayOfWeek.with(TemporalAdjusters.lastDayOfMonth())
        val visibleDates = getDatesBetween(firstDayOfWeek, endDayOfWeek)
        val dates = toUiModel(visibleDates, lastSelectedDate)
        dates.let {
            it.visibleDates.map { date ->
                date.isValidDate = date.date.isAfter(minDate) && date.date.isBefore(maxDate) || date.date.isEqual(minDate) /*|| date.date.isEqual(maxDate)*/
            }
        }
        return dates
    }


    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1
        return Stream.iterate(startDate) { date ->
            date.plusDays(1)
        }.limit(numOfDays).collect(Collectors.toList())
    }

    private fun toUiModel(dateList: List<LocalDate>, lastSelectedDate: LocalDate): CalendarUiModel {
        return CalendarUiModel(
            selectedDate = toItemUiModel(lastSelectedDate, true),
            visibleDates = dateList.map {
                toItemUiModel(it, it.isEqual(lastSelectedDate))
            },
        )
    }

    private fun toItemUiModel(date: LocalDate, isSelectedDate: Boolean) = Date(
        isSelected = isSelectedDate,
        isToday = date.isEqual(today),
        date = date,
    )
    fun getWeekDays(): List<DayOfWeek> {
        return listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
        )
    }

    fun getModifiedDates(date: List<Date>): List<Date> {
        val range = (date.firstOrNull()?.date?.dayOfWeek?.value?.minus(1)) ?: 0
        val mDate = mutableListOf<Date>()
        for (i in 0..<range) {
            mDate.add(i, Date(isSelected = false, isToday = false, date = LocalDate.now(), fake = true)
            )
        }
        mDate.addAll(date)
        return mDate
    }

}