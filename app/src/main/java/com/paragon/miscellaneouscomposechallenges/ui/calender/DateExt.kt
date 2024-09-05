package com.paragon.dvcore.utils.extensions

import android.text.format.DateUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Date formats vars
 */
const val DD_MMM_yyyy = "dd-MMM-yyyy"
const val d_MMM_yyyy = "d MMM yyyy"
const val dd_MMM_yyyy = "dd MMM yyyy"
const val DD_MM_yyyy_HH_mm_ss_EEE = "dd-MM-yyyy HH:mm:ss EEE"
const val DD_MM_yyyy_HH_mm_ss = "dd-MM-yyyy HH:mm:ss"
const val DD_MM_yyyy_hh_mm_ss_a = "dd-MM-yyyy hh:mm:ss a"
const val EEEE_DD_MMM_yyyy_hh_mm_ss_a = "EEEE, dd MMM yyyy hh:mm:ss a"
const val MMM_DD_yyyy_hh_mm_a = "MMM dd, yyyy, hh:mm a"
const val DD_MMM_yyyy_hh_mm_ss = "dd MMM yyyy hh:mm:ss"
const val EEEE_DD_MMM_yyyy_HH_mm_ss = "EEEE, dd MMM yyyy HH:mm:ss"
const val DD_MMM_yyyy_HH_mm_ss = "dd MMM yyyy HH:mm:ss"
const val D_MM_yyyy_slash = "dd/MM/yyyy"
const val DD_MMM_yyyy_slash = "dd/MMM/yyyy"
const val DD_MM_yyyy_HH_mm_ss_slash = "dd/MM/yyyy HH:mm:ss"
const val DD_MM_yy_HH_mm_ss_slash = "dd/MM/yy hh:mm a"
const val DD_MM_yyyy_hh_mm_ss_a_slash = "dd/MM/yyyy hh:mm:ss a"
const val D = "d"
const val DD = "dd"
const val MM = "MM"
const val MMM = "MMM"
const val yyyy = "yyyy"
const val DD_MMM = "dd MMM"
const val D_MMM = "d MMM"
const val yyyy_MM_DD = "yyyy-MM-dd"
const val ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
const val UTC_DATE_FORMAT = "yyyy-MM-d'T'HH:mm:ss.yyyy'Z'"
const val MMM_dd_yyyy = "MMM dd, yyyy"
const val EEE_MMM_dd_yyyy = "EEEE, MMM dd, yyyy"
const val EEE_MMM_dd_yyyy_hh_mm = "EEEE, MMM dd, yyyy hh:mm"
const val UNICODE_PREFIX = "&#x"

/**
 * Time formats vars
 */
const val HH_mm_ss = "HH:mm:ss"
const val HH_mm = "HH:mm"
const val hh_mm_ss_a = "hh:mm:ss a"
const val hh_mm_ss = "hh:mm:ss"
const val hh_mm = "hh:mm"
const val hh_mm_a = "hh:mm a"
const val hh = "hh"
const val HH = "HH"

val Int.asDate: Date
    get() = Date(this.toLong() * 1000L)

fun Date.formatTo(requiredFormat: String, locale: Locale? = Locale.getDefault()): String? {
    var dateStr: String? = null
    val df = SimpleDateFormat(requiredFormat, locale)
    try {
        dateStr = df.format(this)
    } catch (ex: Exception) {
        Log.d("date", ex.toString())
    }
    return dateStr
}

fun Date.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal
}

fun Date.isPastDate(): Boolean {
    return this.before(Date())
}

fun Date.isFutureDate(): Boolean {
    return this.after(Date())
}

fun Date.isTodayDate(): Boolean {
    return DateUtils.isToday(this.time)
}

fun Date.isYesterdaysDate(): Boolean {
    return DateUtils.isToday(this.time + DateUtils.DAY_IN_MILLIS)
}

fun Date.isTomorrowsDate(): Boolean {
    return DateUtils.isToday(this.time - DateUtils.DAY_IN_MILLIS)
}

fun Date.getYesterdaysDate(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, -1)
    return cal.time
}

fun Date.getYesterdaysStringDate(outputFormat: String): String? {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, -1)
    return cal.time.formatTo(outputFormat)
}

fun Date.getTomorrowsDate(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    return cal.time
}

fun Date.getTomorrowsStringDate(outputFormat: String): String? {
    val cal = this.toCalendar()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    return cal.time.formatTo(outputFormat)
}

fun Date.getLastMonthDate(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.MONTH, -1)
    return cal.time
}

fun Date.getLastMonthStringDate(outputFormat: String): String? {
    val cal = this.toCalendar()
    cal.add(Calendar.MONTH, -1)
    return cal.time.formatTo(outputFormat)
}

fun Date.getNextMonthDate(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.MONTH, 1)
    return cal.time
}

fun Date.getNextMonthStringDate(outputFormat: String): String? {
    val cal = this.toCalendar()
    cal.add(Calendar.MONTH, 1)
    return cal.time.formatTo(outputFormat)
}

fun Date.getLastYearDate(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.YEAR, -1)
    return cal.time
}

fun Date.getLastYearStringDate(outputFormat: String): String? {
    val cal = this.toCalendar()
    cal.add(Calendar.YEAR, -1)
    return cal.time.formatTo(outputFormat)
}

fun Date.getNextYearDate(): Date {
    val cal = this.toCalendar()
    cal.add(Calendar.YEAR, 1)
    return cal.time
}

fun Date.getNextYearStringDate(outputFormat: String): String? {
    val cal = this.toCalendar()
    cal.add(Calendar.YEAR, 1)
    return cal.time.formatTo(outputFormat)
}

fun Date.addMinutes(minutes: Int, outputFormat: String): String? {
    val cal = this.toCalendar()
    cal.add(Calendar.MINUTE, minutes)
    return cal.time.formatTo(outputFormat)
}

fun Date.hour(required24HrFormat: Boolean = false): Int {
    return this.toCalendar().get(if (required24HrFormat) Calendar.HOUR_OF_DAY else Calendar.HOUR)
}

fun Date.minute(): Int {
    return this.toCalendar().get(Calendar.MINUTE)
}

fun Date.second(): Int {
    return this.toCalendar().get(Calendar.SECOND)
}

fun Date.month(): Int {
    return this.toCalendar().get(Calendar.MONTH) + 1
}

fun Date.monthName(locale: Locale = Locale.getDefault()): String? {
    return this.toCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, locale)
}

fun Date.year(): Int {
    return this.toCalendar().get(Calendar.YEAR)
}

fun Date.day(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_MONTH)
}

fun Date.dayOfWeek(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_WEEK)
}

fun Date.dayOfWeekName(locale: Locale = Locale.getDefault()): String? {
    return this.toCalendar().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale)
}

fun Date.dayOfYear(): Int {
    return this.toCalendar().get(Calendar.DAY_OF_YEAR)
}

fun Date?.differenceBetweenDates(currentDate: Date?): Long {
    return this?.time?.let { currentDate?.time?.minus(it) } ?: 0
}

fun Date.convertToISOFormat(): String {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        sdf.format(this)
    } catch (e: ParseException) {
        ""
    }
}

fun Date.addYears(years: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.YEAR, years)
    return calendar.time
}

fun Date.addMonths(months: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.MONTH, months)
    return calendar.time
}

fun Date.addDays(days: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_YEAR, days)
    return calendar.time
}

fun Date.addWeeks(weeks: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.WEEK_OF_YEAR, weeks)
    return calendar.time
}

fun Date.addHours(hours: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.HOUR_OF_DAY, hours)
    return calendar.time
}

fun Date.addMinutes(minutes: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.MINUTE, minutes)
    return calendar.time
}

fun Date.addSeconds(seconds: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.SECOND, seconds)
    return calendar.time
}
fun Date.localToUTC(): Date {
    return Date(this.time + Calendar.getInstance().getTimeZone().getOffset(Date().time))
}