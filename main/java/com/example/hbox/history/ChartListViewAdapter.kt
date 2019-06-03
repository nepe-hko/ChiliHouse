package com.history

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.hbox.history.axisStyler.TemperatureAxisStyler
import com.example.hbox.history.dataSetStyler.TemperaturDataSetStyler
import com.example.hbox.history.limitLinesFormatter.WeekdayLimitLinesCreator
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class ChartListViewAdapter(private val context: Context, private val sensorList: List<Sensor>) : BaseAdapter() {

    override fun getItem(position: Int): Sensor = sensorList[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = sensorList.size

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val chartView = LayoutInflater.from(context).inflate(com.example.hbox.R.layout.row_layout, parent, false)
        val view = chartView.findViewById<LineChart>(com.example.hbox.R.id.chart)

        // add entries
        val entries: MutableList<Entry> = mutableListOf()
        sensorList[position].data?.forEach {
            entries.add(Entry(it.date.toFloat(), it.value))
        }

        // style dataset
        val lineDataSet = LineDataSet(entries, sensorList[position].name)
        TemperaturDataSetStyler.styleDataSet(lineDataSet)

        // style axis
        TemperatureAxisStyler.styleXAxis(view.xAxis)
        TemperatureAxisStyler.styleYAxisLeft(view.axisLeft)
        TemperatureAxisStyler.styleYAxisRight(view.axisRight)

        // add weekdays
        WeekdayLimitLinesCreator.create(view.xAxis, sensorList[position])

        return view.apply {
            data = LineData(lineDataSet)
            isScaleYEnabled = false
            description.isEnabled = false
            setVisibleXRangeMinimum(86400000f)
            setVisibleXRangeMaximum(86400000f*3)
            moveViewToX(sensorList[position].data?.last()!!.date.toFloat().minus(86400000*3 ))
        }
    }
}