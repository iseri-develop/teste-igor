package com.example.testeaiko.service.model

import com.google.gson.annotations.SerializedName

class PositionVehicleModel {

    @SerializedName("hr")
    val hour: String = ""

    @SerializedName("l")
    val listLines: List<LineModel> = listOf()

}