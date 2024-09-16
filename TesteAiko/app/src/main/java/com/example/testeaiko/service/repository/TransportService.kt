package com.example.testeaiko.service.repository

import com.example.testeaiko.service.model.LineModel
import com.example.testeaiko.service.model.PositionVehicleModel
import com.example.testeaiko.service.model.StopModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransportService {

    //Autenticar API
    @POST("Login/Autenticar")
    fun auth(@Query("token") token: String): Call<Boolean>

    //Listar Paradas
    @GET("Parada/Buscar")
    fun getListStops(@Query("termosBusca") filter: String?): Call<List<StopModel>>

    //Listar Posições dos Veículos
    @GET("Posicao")
    fun getPositionVehicles(): Call<PositionVehicleModel>

    //Listar Linhas
    @GET("Linha/Buscar")
    fun getLines(@Query("termosBusca") filter: String?): Call<List<LineModel>>

}