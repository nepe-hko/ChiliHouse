package com.history

import com.google.gson.annotations.SerializedName

data class History(@SerializedName("sensors")
                   val sensors: List<Sensor>?)