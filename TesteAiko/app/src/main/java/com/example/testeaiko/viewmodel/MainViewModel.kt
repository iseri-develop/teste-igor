package com.example.testeaiko.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testeaiko.service.repository.TransportRepository
import com.example.testeaiko.service.listener.APIListener
import com.example.testeaiko.service.model.PositionVehicleModel
import com.example.testeaiko.service.model.StopModel
import com.example.testeaiko.service.model.VehicleModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val transportRepository = TransportRepository(application.applicationContext)

    private val _auth = MutableLiveData<Boolean>()
    val auth: MutableLiveData<Boolean> = _auth

    private val _stops = MutableLiveData<List<StopModel>>()
    val stops: MutableLiveData<List<StopModel>> = _stops

    private val _vehicles = MutableLiveData<List<VehicleModel>>()
    val vehicles: MutableLiveData<List<VehicleModel>> = _vehicles

    fun authenticate() {
        transportRepository.authenticate(object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _auth.value = result
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun listStops(filter: String?) {
        transportRepository.listStops(filter, object : APIListener<List<StopModel>> {
            override fun onSuccess(result: List<StopModel>) {
                _stops.value = result
            }

            override fun onFailure(message: String) {

            }

        })
    }

    fun listPositionVehicles() {
        transportRepository.getPositionVehicles(object : APIListener<PositionVehicleModel> {
            override fun onSuccess(result: PositionVehicleModel) {
                val list = mutableListOf<VehicleModel>()

                result.listLines.forEach { line ->
                    list.addAll(line.listVehicles)
                }

                _vehicles.value = list
            }

            override fun onFailure(message: String) {

            }

        })
    }
}