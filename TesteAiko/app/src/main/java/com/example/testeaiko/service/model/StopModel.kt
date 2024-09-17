package com.example.testeaiko.service.model

import com.google.gson.annotations.SerializedName

class StopModel {

    @SerializedName("cp")
    val idStop: Int = 0

    @SerializedName("np")
    val nameStop: String = ""

    @SerializedName("ed")
    val addressStop: String = ""

    @SerializedName("py")
    val latitudeStop: Double = 0.0

    @SerializedName("px")
    val longitudeStop: Double = 0.0

    @SerializedName("l")
    val listLines: List<LineModel> = listOf()
}