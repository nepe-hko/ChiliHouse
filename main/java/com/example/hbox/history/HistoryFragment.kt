package com.example.hbox.history


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.hbox.R
import com.example.hbox.db.SensorRepository
import com.google.gson.Gson
import com.history.ChartListViewAdapter
import com.history.History
import com.history.Sensor

class HistoryFragment : Fragment() {

    lateinit var historyListView: ListView
    val Sensor = SensorRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyListView = view.findViewById(R.id.history_listView)
        Sensor.connect()

        val sensorList = ArrayList<Sensor>()
        val history = Gson().fromJson(jsonString, History::class.java)
        history.sensors?.forEach { sensorList.add(it) }
        if (activity == null) return
        val adapter = ChartListViewAdapter(activity!!.applicationContext, sensorList)
        historyListView.adapter = adapter
    }

}