
package com.example.hbox.control

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hbox.R
import com.example.hbox.model.Device
import com.example.hbox.model.DeviceList
import com.google.gson.Gson
import com.history.ChartListViewAdapter
import java.net.HttpURLConnection
import java.net.URL

class ManuallyFragment : Fragment() {
/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        // fetch all available devices from server
        val url = "http://87.156.139.113:3000/devices"
        AsyncTaskHandleJson().execute(url)
        // insert device switches in Layout
        return inflater.inflate(R.layout.fragment_control_manually, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //create onClick listeners

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

        val deviceList = ArrayList<Device>()
        val devices = Gson().fromJson(jsonString, DeviceList::class.java)
        devices.devices?.forEach { deviceList.add(it) }
        if (activity == null) return
        val adapter = ChartListViewAdapter(activity!!.applicationContext, sensorList)
        //historyListView.adapter = adapter
    }
*/
}
