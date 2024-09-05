package com.paragon.miscellaneouscomposechallenges.ui.calender

import com.paragon.dvcore.utils.extensions.getDate
import com.paragon.dvcore.utils.extensions.yyyy_MM_DD
import java.util.Calendar
import java.util.Date

class ValidationHelper(private val customDate : Pair<String , String> = Pair("", "")) {
    private val minDate: Calendar = Calendar.getInstance()
    private val maxDate: Calendar = Calendar.getInstance()
    fun invoke(validators: DateValidator, setDate: (start: Date, end: Date) -> Unit) {
        when (validators) {
            DateValidator.THIS_WEEK -> {
                minDate.time = minDate.time
                maxDate.time = minDate.time
                maxDate.add(Calendar.DAY_OF_YEAR, 7)
            }
            DateValidator.THIS_MONTH -> {
                minDate.set(Calendar.DAY_OF_MONTH, 1)
                maxDate.time = minDate.time
                maxDate.set(Calendar.DAY_OF_MONTH, minDate.getActualMaximum(Calendar.DAY_OF_MONTH))
            }
            DateValidator.THIS_YEAR -> {
                minDate.set(Calendar.MONTH, Calendar.JANUARY)
                minDate.set(Calendar.DAY_OF_MONTH, 1)
                maxDate.time = minDate.time
                maxDate.set(Calendar.MONTH, Calendar.DECEMBER)
                maxDate.set(Calendar.DAY_OF_MONTH, maxDate.getActualMaximum(Calendar.DAY_OF_MONTH))
            }
            DateValidator.NEXT_15_DAYS -> {
                minDate.time = minDate.time
                maxDate.time = maxDate.apply { add(Calendar.DATE , 15) }.time
            }

            DateValidator.NEXT_THREE_MONTHS -> {
                minDate.time = minDate.time
                maxDate.time = maxDate.apply { add(Calendar.MONTH, 3) }.time
            }

            DateValidator.NEXT_SIX_MONTHS -> {
                minDate.time = minDate.time
                maxDate.time = maxDate.apply { add(Calendar.MONTH, 6)  }.time
            }

            DateValidator.CUSTOM_DATE_RANGE -> {
                minDate.time = Date(-1)
                maxDate.time = Date(Long.MAX_VALUE)
                customDate.first.getDate(yyyy_MM_DD)?.let { minDate.time = it }
                customDate.second.getDate(yyyy_MM_DD)?.let { maxDate.time = it }
            }

            DateValidator.UNSPECIFIED -> return
        }
        setDate(minDate.time, maxDate.time)
    }
}