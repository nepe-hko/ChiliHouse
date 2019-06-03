package com.example.hbox.history.axisFormatter

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.DecimalFormat

class NullDigitsFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {

        val df = DecimalFormat("#")
        //df.roundingMode = RoundingMode.CEILING
        return df.format(value) + " °C"
    }
}