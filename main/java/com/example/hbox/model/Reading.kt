package com.history

import com.google.gson.annotations.SerializedName

data class Reading(@SerializedName("date")
                    val date: String = "",
                   @SerializedName("value")
                    val value: Float = -1f)