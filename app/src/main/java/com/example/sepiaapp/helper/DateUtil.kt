package com.example.sepiaapp.helper

import androidx.annotation.VisibleForTesting
import com.example.sepiaapp.model.Config
import java.util.*
import javax.inject.Inject

class DateUtil @Inject constructor(
    private val gsonHelper: GsonHelper
) {
    sealed class Day(val name: String, val value: Int) {
        object SUNDAY : Day("U", Calendar.SUNDAY)
        object MONDAY : Day("M", Calendar.MONDAY)
        object TUESDAY : Day("T", Calendar.TUESDAY)
        object WEDNESDAY : Day("W", Calendar.WEDNESDAY)
        object THURSDAY : Day("R", Calendar.THURSDAY)
        object FRIDAY : Day("F", Calendar.FRIDAY)
        object SATURDAY : Day("S", Calendar.SATURDAY)

        companion object {
            fun parse(name: String): Day? {
                return when (name) {
                    SUNDAY.name -> SUNDAY
                    MONDAY.name -> MONDAY
                    TUESDAY.name -> TUESDAY
                    WEDNESDAY.name -> WEDNESDAY
                    THURSDAY.name -> THURSDAY
                    FRIDAY.name -> FRIDAY
                    SATURDAY.name -> SATURDAY
                    else -> null
                }
            }
        }
    }

    fun isValidTimeFromConfig(): Boolean {
        val config = getConfig()
        val workHours = config.settings.workHours
        val listConfig: List<String> = workHours.split(" ")
        val days = listConfig[0].split("-")
        val startTime = getTime(listConfig[1])
        val endTime = getTime(listConfig[3])
        val startDay = Day.parse(days[0])?.value
        val endDay = Day.parse(days[1])?.value
        if (startDay != null && endDay != null && startTime != null && endTime != null) {
            return if (isDayOfWeekInBetween(startDay, endDay)) {
                isNowBetweenDateTime(startTime, endTime)
            } else {
                false
            }
        }
        return false
    }

    private fun isNowBetweenDateTime(s: Date?, e: Date?): Boolean {
        val now = getDateNow()
        return now.after(s) && now.before(e)
    }

    @VisibleForTesting
    fun getDateNow() = Date()

    private fun getTime(format: String): Date? {
        val hms: List<String> = format.split(":")
        val gc = GregorianCalendar()
        gc.set(Calendar.HOUR_OF_DAY, hms[0].toInt())
        gc.set(Calendar.MINUTE, hms[1].toInt())
        return gc.time
    }

    @VisibleForTesting
    fun getConfig(): Config {
        return gsonHelper.getTimeConfig()
    }

    private fun isDayOfWeekInBetween(start: Int, end: Int): Boolean {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]
        if (day in start..end) {
            return true
        }
        return false
    }
}