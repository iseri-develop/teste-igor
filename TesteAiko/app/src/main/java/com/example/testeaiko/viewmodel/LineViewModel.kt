package com.example.testeaiko.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testeaiko.service.listener.APIListener
import com.example.testeaiko.service.model.LineModel
import com.example.testeaiko.service.repository.TransportRepository

class LineViewModel(application: Application) : AndroidViewModel(application) {
    private val transportRepository = TransportRepository(application.applicationContext)

    private val _lines = MutableLiveData<List<LineModel>>()
    val lines: MutableLiveData<List<LineModel>> = _lines

    fun listLines(filter: String) {
        transportRepository.getLine(filter, object : APIListener<List<LineModel>> {
            override fun onSuccess(result: List<LineModel>) {
                _lines.value = result
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }
        })
    }
}