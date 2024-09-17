package com.example.testeaiko.service.model

import com.google.gson.annotations.SerializedName

class VehicleModel {

    @SerializedName("p")
    val prefix: Int = 0

    @SerializedName("t")
    val expectedTime: String = ""

    @SerializedName("a")
    val accesibleDisable: Boolean = false

    @SerializedName("ta")
    val hourCaptured: String = ""

    @SerializedName("py")
    val latitude: Double = 0.0

    @SerializedName("px")
    val longitude: Double = 0.0
}