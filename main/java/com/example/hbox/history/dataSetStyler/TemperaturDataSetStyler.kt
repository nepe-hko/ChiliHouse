package com.example.hbox.history.dataSetStyler

import android.graphics.Color
import com.github.mikephil.charting.data.LineDataSet

class TemperaturDataSetStyler {
    companion object {
        fun styleDataSet (lineDataSet : LineDataSet) : LineDataSet {
            return lineDataSet.apply{
                lineWidth = 0f
                color = Color.argb(255,255,15,15)
                fillColor = Color.argb(255,255,15,15)
                fillAlpha = 100
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true)
                setDrawCircles(false)
                setDrawValues(false)
            }
        }
    }
}