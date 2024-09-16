package com.example.testeaiko.service.model

import com.google.gson.annotations.SerializedName

class LineModel {

    //Código identificador da linha. Este é um código identificador único de cada linha do sistema (por sentido de operação)
    @SerializedName("cl")
    val idLine: Int = 0

    //Letreiro completo
    @SerializedName("c")
    val nameLine: String = ""

    // Indica se uma linha opera no modo circular (sem um terminal secundário)
    @SerializedName("lc")
    val lineCircle: Boolean = false

    // Letreiro de destino da linha
    @SerializedName("lt0")
    val lineDestiny: String = ""

    // Letreiro de origem da linha
    @SerializedName("lt1")
    val lineOrigin: String = ""

    //Informa a primeira parte do letreiro numérico da linha
    @SerializedName("lt")
    val firstLetterLine: String = ""

    //Informa a segunda parte do letreiro numérico da linha, que indica se a linha opera nos modos:
    //BASE (10), ATENDIMENTO (21, 23, 32, 41)
    @SerializedName("tl")
    val modeLine: Int = 0

    //Informa o sentido ao qual a linha atende, onde 1 significa Terminal Principal para Terminal Secundário e 2 para Terminal Secundário para Terminal Principal
    @SerializedName("sl")
    val senseLine: Int = 0

    // Informa o letreiro descritivo da linha no sentido Terminal Principal para Terminal Secundário
    @SerializedName("tp")
    val terminalPrimary: String = ""

    //Informa o letreiro descritivo da linha no sentido Terminal Secundário para Terminal Principal
    @SerializedName("ts")
    val terminalSecondary: String = ""

    //Informa a quantidade de veículos localizados
    @SerializedName("qv")
    val qtdeVehicle: Int = 0

    @SerializedName("vs")
    val listVehicles: List<VehicleModel> = listOf()
}