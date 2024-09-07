package com.paragon.dvcore.utils.extensions

import java.util.Calendar
import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit
import kotlin.math.abs

val String.asDate: Date
    get() = Date(this.toLong() * 1000L)

fun String.formatTo(inputFormat: String, outputFormat: String): String? {
    return try {
        DateFormat.format(
            outputFormat,
            SimpleDateFormat(inputFormat, Locale.getDefault()).parse(this)
        ).toString().uppercase(Locale.getDefault())
    } catch (e: ParseException) {
        ""
    }
}

fun String.formatIsoTo(outputFormat: String): String? {
    return try {
        if (this.isNotEmpty()) {
            val date = SimpleDateFormat(ISO_8601_DATE_FORMAT, Locale.getDefault())
                .parse(this.replace("Z$".toRegex(), "+0000"))
            val dateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
            dateFormat.timeZone = TimeZone.getDefault()
            date?.let { dateFormat.format(it) }
        } else {
            ""
        }
    } catch (e: ParseException) {
        ""
    }
}

fun String.convertIsoToDate(): Date? {
    return try {
        if (this.isNotEmpty()) {
            SimpleDateFormat(ISO_8601_DATE_FORMAT, Locale.getDefault()).apply {
                this.timeZone = TimeZone.getDefault()
            }.parse(this.replace("Z$".toRegex(), "+0000"))
        } else {
            null
        }
    } catch (e: ParseException) {
        null
    }
}

fun String.convertToISOFormat(inputFormat: String): String? {
    return try {
        if (this.isNotEmpty()) {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            SimpleDateFormat(inputFormat, Locale.getDefault()).parse(
                this
            )?.let {
                sdf.format(
                    it
                ).toString()
            }
        } else {
            ""
        }
    } catch (e: ParseException) {
        ""
    }
}

fun String.isFutureDate(inputFormat: String): Boolean {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.isFutureDate() ?: false
}

fun String.isPastDate(inputFormat: String): Boolean {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.isPastDate() ?: false
}

fun String.isTodayDate(inputFormat: String): Boolean {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.isTodayDate() ?: false
}

fun String.isYesterdaysDate(inputFormat: String): Boolean {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.isYesterdaysDate() ?: false
}

fun String.isTomorrowsDate(inputFormat: String): Boolean {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.isTomorrowsDate() ?: false
}

fun String.getDate(inputFormat: String): Date? {
    return try {
        val format = SimpleDateFormat(inputFormat, Locale.getDefault())
        format.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun String.getYesterdaysDate(inputFormat: String): Date? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getYesterdaysDate()
}

fun String.getYesterdayStringDate(inputFormat: String, outputFormat: String): String? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getYesterdaysStringDate(outputFormat)
}

fun String.getTomorrowsDate(inputFormat: String): Date? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getTomorrowsDate()
}

fun String.getTomorrowsStringDate(inputFormat: String, outputFormat: String): String? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getTomorrowsStringDate(outputFormat)
}

fun String.getLastMonthDate(inputFormat: String): Date? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getLastMonthDate()
}

fun String.getLastMonthStringDate(inputFormat: String, outputFormat: String): String? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getLastMonthStringDate(outputFormat)
}

fun String.getNextMonthDate(inputFormat: String): Date? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getNextMonthDate()
}

fun String.getNextMonthStringDate(inputFormat: String, outputFormat: String): String? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getNextMonthStringDate(outputFormat)
}

fun String.getLastYearDate(inputFormat: String): Date? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getLastYearDate()
}

fun String.getLastYearStringDate(inputFormat: String, outputFormat: String): String?{
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getLastYearStringDate(outputFormat)
}

fun String.getNextYearDate(inputFormat: String): Date? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getNextYearDate()
}

fun String.getNextYearStringDate(inputFormat: String, outputFormat: String): String? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.getNextYearStringDate(outputFormat)
}

fun String.hour(inputFormat: String,required24HrFormat: Boolean = false): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.hour(required24HrFormat)
}

fun String.minute(inputFormat: String): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.minute()
}

fun String.second(inputFormat: String): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.second()
}

fun String.month(inputFormat: String): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.month()
}

fun String.monthName(inputFormat: String): String? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.monthName()
}

fun String.year(inputFormat: String): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.year()
}

fun String.day(inputFormat: String): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.day()
}

fun String.dayOfWeek(inputFormat: String): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.dayOfWeek()
}

fun String.dayOfWeekName(inputFormat: String): String? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.dayOfWeekName()
}

fun String.dayOfYear(inputFormat: String): Int? {
    val format = SimpleDateFormat(inputFormat, Locale.getDefault())
    return format.parse(this)?.dayOfYear()
}

fun String?.toDisplayDate(inputFormat: String = "",outputFormat: String = MMM_DD_yyyy_hh_mm_a, isIsoFormat: Boolean = false): String {
    return if (this != null) {
        val date = if (isIsoFormat) {
            this.convertIsoToDate()
        } else {
            this.getDate(inputFormat)
        }
        if (date?.isTodayDate() == true)
            "Today, " + date.formatTo(hh_mm_a).orEmpty()
        else if (date?.isTomorrowsDate() == true)
            "Tomorrow, " + date.formatTo(hh_mm_a).orEmpty()
        else
            date?.formatTo(outputFormat).orEmpty()
    } else
        ""
}

fun String.roundMinute(interval: Int): String {
    val hour = this.takeWhile { it.isDigit() }.toInt()
    var mins = this.takeLastWhile { it.isDigit() }.toInt()
    return if (mins % interval == 0) {
        if (hour == 23 && mins > 55) {
            mins = 55
        }
        "${String.format("%02d", hour)}:${String.format("%02d", mins)}"
    } else {
        if (hour == 23 && mins > 55) {
            "23:55"
        } else {
            mins = mins - (mins % interval) + interval
            if (mins == 60) {
                mins = 0
                "${String.format("%02d", hour + 1)}:${String.format("%02d", mins)}"
            } else {
                "${String.format("%02d", hour)}:${String.format("%02d", mins)}"
            }
        }
    }
}

fun isTimeIsInTimeRange(
    slotStartTime: String?,
    slotEndTime: String?,
    selectedTimeCalendar: Calendar? = Calendar.getInstance().apply { time = Date() },
    futureDate: Date? = null,
): Int {
    if (slotStartTime.isNullOrEmpty().not()
        && slotStartTime?.split(":").isNullOrEmpty().not() && slotStartTime?.split(":")?.size!! >= 2
        && slotEndTime.isNullOrEmpty().not()
        && slotEndTime?.split(":").isNullOrEmpty().not() && slotEndTime?.split(":")?.size!! >= 2) {
        return Pair(Calendar.getInstance().apply {
            time = futureDate ?: Date()
            slotStartTime.split(":").let { hhMmList ->
                set(Calendar.HOUR_OF_DAY, hhMmList[0].toInt())
                set(Calendar.MINUTE, hhMmList[1].toInt())
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
        }.time.formatTo(DD_MMM_yyyy_HH_mm_ss), Calendar.getInstance().apply {
            time = futureDate ?: Date()
            slotEndTime.split(":").let { hhMmList ->
                set(Calendar.HOUR_OF_DAY, hhMmList[0].toInt())
                set(Calendar.MINUTE, hhMmList[1].toInt())
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
        }.time.formatTo(DD_MMM_yyyy_HH_mm_ss)).let {
            try {
                if (selectedTimeCalendar?.time?.before(it.first?.getDate(DD_MMM_yyyy_HH_mm_ss)) == true)                   // is before start time of range
                    1
                else if (                                                                                   // is in time range
                    (selectedTimeCalendar?.time?.after(it.first?.getDate(DD_MMM_yyyy_HH_mm_ss)) == true || selectedTimeCalendar?.time?.equals(it.first?.getDate(DD_MMM_yyyy_HH_mm_ss))  == true) &&
                    (selectedTimeCalendar.time.before(it.second?.getDate(DD_MMM_yyyy_HH_mm_ss)) || selectedTimeCalendar.time.equals(it.second?.getDate(DD_MMM_yyyy_HH_mm_ss)))
                ) 0
                else -1                                                                                     // is not in range
            } catch (e: Exception) {
                -1
            }
        }
    } else return -1
}

fun addMinutesInTime(startDate: Calendar?, startTime: String?, duration: Int): Calendar? {
    startTime?.split(":")?.let {
        if (it.size >= 2) return try {
            val cal = Calendar.getInstance()
            cal.time = startDate?.time
            cal.set(Calendar.HOUR_OF_DAY, it.first().toInt())
            cal.set(Calendar.MINUTE, it[1].toInt())
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            cal.add(Calendar.MINUTE, duration)
            cal
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }
    return null
}

fun Pair<String, String>.getSlotDurationInMinutes(startTimeFormat: String = HH_mm, endTimeFormat: String = HH_mm): Long {
    val startTimeInMillis = first.getDate(startTimeFormat)?.time ?: 0L
    val endTimeInMillis = second.getDate(endTimeFormat)?.time ?: 0L
    return (endTimeInMillis - startTimeInMillis) / (1000 * 60)
}

fun Long.getRemainingTime(
    dayLabel: String = "d",
    hourLabel: String = "h",
    minLabel: String = "m",
    secLabel: String = "s",
    showSeconds: Boolean = false
): String {
    val dayCount = abs(TimeUnit.MILLISECONDS.toDays(this))
    val hourCount = abs(TimeUnit.MILLISECONDS.toHours(this))
    val minCount = abs(TimeUnit.MILLISECONDS.toMinutes(this))
    val secCount = abs(TimeUnit.MILLISECONDS.toSeconds(this))

    if (dayCount > 0) {
        return with(StringBuilder()) {
            if (dayCount > 1) {
                append("$dayCount $dayLabel")
            }
            if ((hourCount % 24).toInt() != 0) {
                append(" ${hourCount % 24} $hourLabel")
            }
            toString()
        }
    } else if (minCount > 0) {
        val hours = minCount / 60
        val minutes = minCount % 60
        return if (hours > 0) {
            "${hours}${hourLabel} ${(if (minutes > 0) minutes else 60) % 60}${minLabel}"
        } else {
            "$minutes$minLabel"
        }
    } else if (showSeconds && secCount > 0) {
        return "$secCount$secLabel"
    } else if (secCount > 0) {
        return "1$minLabel"
    }
    return ""
}