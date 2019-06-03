package com.example.hbox.history.axisFormatter

import android.text.format.DateFormat
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.util.*

class TimeFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val calendar = Calendar.getInstance(Locale.GERMAN)
        calendar.timeInMillis = value.toLong()
        return DateFormat.format("HH:mm", calendar).toString()
    }
}
