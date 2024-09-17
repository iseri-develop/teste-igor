package com.example.testeaiko.service.model

import com.google.android.gms.maps.model.LatLng

class MarkerModel {

    var title: String = ""

    var snippet: String = ""

    var position: LatLng = LatLng(0.0, 0.0)

    var idStop: Int = 0
}