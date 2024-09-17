package com.example.testeaiko.service.model

import com.google.gson.annotations.SerializedName

class ArrivalForecastModel {

    @SerializedName("hr")
    val hour: String = ""

    @SerializedName("p")
    val stop = StopModel()
}