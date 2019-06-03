package com.example.hbox.model

import com.google.gson.annotations.SerializedName

data class Device(@SerializedName("active")
                  val active: Boolean = false,
                  @SerializedName("name")
                  val name: String = "",
                  @SerializedName("id")
                  val id: Int = 0,
                  @SerializedName("pin")
                  val pin: Int = 0)