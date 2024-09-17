package com.example.testeaiko.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.testeaiko.R
import com.example.testeaiko.service.model.MarkerModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomMarkerInfoStopAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        val markerModel = marker.tag as? MarkerModel ?: return null

        val view = LayoutInflater.from(context).inflate(R.layout.custom_marker_info_stop, null)

        // Preencha a View com os dados do marcador
        view.findViewById<TextView>(R.id.text_name_stop).text = markerModel.title
        view.findViewById<TextView>(R.id.text_address_stop).text = markerModel.snippet

        return view
    }

    override fun getInfoWindow(marker: Marker): View? = null

}