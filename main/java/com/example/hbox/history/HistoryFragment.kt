package com.example.hbox.history


import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.hbox.R
import com.google.gson.Gson
import com.history.ChartListViewAdapter
import com.history.History
import com.history.Sensor
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class HistoryFragment : Fragment() {

    lateinit var historyListView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyListView = view.findViewById(R.id.history_listView)
        val url = "http://87.148.35.81:3000/history"
        AsyncTaskHandleJson().execute(url)
    }

    inner class AsyncTaskHandleJson : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {

            var text: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                text = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                connection.disconnect()
            }
            return text
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            handleJson(result)
        }
    }

    private fun handleJson(jsonString: String?) {

        val sensorList = ArrayList<Sensor>()
        val history = Gson().fromJson(jsonString, History::class.java)
        history.sensors?.forEach { sensorList.add(it) }
        if (activity == null) return
        val adapter = ChartListViewAdapter(activity!!.applicationContext, sensorList)
        historyListView.adapter = adapter
    }
}