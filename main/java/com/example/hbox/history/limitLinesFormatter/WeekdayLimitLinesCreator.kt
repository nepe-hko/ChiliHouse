package com.example.hbox.history.limitLinesFormatter

import android.graphics.Color
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.history.Sensor
import java.text.SimpleDateFormat
import java.util.*

class WeekdayLimitLinesCreator {
    companion object {
        fun create(xAxis: XAxis, sensor : Sensor) {
            val secondsPerDay = 86400000L
            val firstTimestamp = sensor.data?.first()?.date!!.toLong()
            val lastTimestamp = sensor.data?.last()?.date!!.toLong()
            val firstDay = firstTimestamp - firstTimestamp?.rem(secondsPerDay) - 3600000

            for (a in firstDay..lastTimestamp step secondsPerDay) {
                val calendar = Calendar.getInstance(Locale.GERMAN)
                calendar.timeInMillis = a
                val weekday = SimpleDateFormat("EEE", Locale.GERMAN).format(calendar.time.time)
                val dayLimitLine = LimitLine(a.toFloat(), weekday)
                dayLimitLine.textSize = 22f
                dayLimitLine.lineWidth = 2f
                dayLimitLine.textColor = Color.LTGRAY
                dayLimitLine.lineColor = Color.LTGRAY
                xAxis.addLimitLine(dayLimitLine)
            }
        }
    }
}