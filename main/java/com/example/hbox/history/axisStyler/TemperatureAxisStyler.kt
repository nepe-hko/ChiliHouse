package com.example.hbox.history.axisStyler

import android.graphics.Color
import com.example.hbox.history.axisFormatter.TemperatureFormatter
import com.example.hbox.history.axisFormatter.TimeFormatter
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis

class TemperatureAxisStyler {
    companion object {
        fun styleXAxis(xAxis: XAxis) : XAxis {
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            return xAxis.apply {
                valueFormatter = TimeFormatter()
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setDrawLimitLinesBehindData(true)
                textSize = 14f
            }
        }

        fun styleYAxisRight(yAxis: YAxis) : YAxis {
            return yAxis.apply {
                setDrawAxisLine(false)
                setDrawLabels(false)
                setDrawGridLines(false)
            }
        }

        fun styleYAxisLeft(yAxis: YAxis) : YAxis {
            return yAxis.apply {
                setDrawAxisLine(false)
                textSize = 14f
                valueFormatter = TemperatureFormatter()
                axisMinimum = 16f
                axisMaximum = 36f
                setDrawGridLines(true)
                gridColor = Color.DKGRAY
                setLabelCount(11,true)

            }
        }
    }

}