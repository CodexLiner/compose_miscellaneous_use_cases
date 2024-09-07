package com.paragon.miscellaneouscomposechallenges.ui.calendarScreen.calender

import android.os.Parcel
import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints
import com.paragon.dvcore.utils.extensions.formatTo
import com.paragon.dvcore.utils.extensions.getDate
import com.paragon.dvcore.utils.extensions.yyyy_MM_DD
import java.util.Calendar
import java.util.Date

class CalendarValidator(private val validator: DateValidator, private var customDateRange: Pair<String, String> = Pair("", "")) : CalendarConstraints.DateValidator,
    Parcelable {

    private val selectedDate = Calendar.getInstance()
    private var minDate: Date? = null
    private var maxDate: Date? = null

    override fun isValid(date: Long): Boolean {
        selectedDate.timeInMillis = date

        // Get minDate and maxDate from the validator function
        ValidationHelper(customDateRange).invoke(validators = validator) { minDate, maxDate ->
            this.minDate = minDate.formatTo(yyyy_MM_DD)?.getDate(yyyy_MM_DD)
            this.maxDate = maxDate.formatTo(yyyy_MM_DD)?.getDate(yyyy_MM_DD)
        }
        return selectedDate.time >= minDate && selectedDate.time <= maxDate
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        // No special parceling required
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalendarValidator> {
        override fun createFromParcel(source: Parcel?): CalendarValidator {
            return CalendarValidator(DateValidator.UNSPECIFIED)
        }

        override fun newArray(size: Int): Array<CalendarValidator?> {
            return arrayOfNulls(size)
        }
    }
}
