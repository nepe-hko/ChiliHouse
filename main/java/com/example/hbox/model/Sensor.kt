package com.history

import com.google.gson.annotations.SerializedName

data class Sensor(@SerializedName("id")
                       val id: String = "",
                  @SerializedName("name")
                       val name: String = "",
                  @SerializedName("type")
                       val type: String = "",
                  @SerializedName("data")
                       val data: List<Reading>?)