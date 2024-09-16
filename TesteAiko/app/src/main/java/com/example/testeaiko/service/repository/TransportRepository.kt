package com.example.testeaiko.service.repository

import android.content.Context
import com.example.testeaiko.helper.Constants
import com.example.testeaiko.service.listener.APIListener
import com.example.testeaiko.service.model.LineModel
import com.example.testeaiko.service.model.PositionVehicleModel
import com.example.testeaiko.service.model.StopModel

class TransportRepository(context: Context) : BaseRepository(context) {
    private val remote = RetrofitClient.getService(TransportService::class.java)

    fun authenticate(listener: APIListener<Boolean>) {
        executeCall(remote.auth(Constants.HEADER.TOKEN_KEY), listener)
    }

    fun listStops(filter: String?, listener: APIListener<List<StopModel>>) {
        if (filter == null)
            executeCall(remote.getListStops(""), listener)
        else
            executeCall(remote.getListStops(filter), listener)
    }

    fun getPositionVehicles(listener: APIListener<PositionVehicleModel>) {
        executeCall(remote.getPositionVehicles(), listener)
    }

    fun getLine(filter: String, listener: APIListener<List<LineModel>>) {
        executeCall(remote.getLines(filter), listener)
    }
}
