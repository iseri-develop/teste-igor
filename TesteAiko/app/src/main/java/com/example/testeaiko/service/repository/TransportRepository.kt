package com.example.testeaiko.service

import android.content.Context
import com.example.testeaiko.R
import com.example.testeaiko.service.listener.APIListener
import com.example.testeaiko.service.model.LineModel
import com.example.testeaiko.service.model.PositionVehicleModel
import com.example.testeaiko.service.model.StopModel
import com.example.testeaiko.service.repository.BaseRepository
import com.example.testeaiko.service.repository.RetrofitClient
import com.example.testeaiko.service.repository.TransportService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransportRepository(context: Context) : BaseRepository(context) {
    private val remote = RetrofitClient.getService(TransportService::class.java)

    fun authenticate(listener: APIListener<Boolean>) {
        executeCall(remote.auth(Constants.HEADER.TOKEN_KEY), listener)
    }

    fun listStops(filter: String?, listener: APIListener<List<StopModel>>) {
        executeCall(remote.getListStops(filter), listener)
    }

    fun getPositionVehicles(listener: APIListener<PositionVehicleModel>) {
        executeCall(remote.getPositionVehicles(), listener)
    }
}
