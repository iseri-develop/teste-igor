package com.example.testeaiko.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.testeaiko.R
import com.example.testeaiko.helper.Constants
import com.example.testeaiko.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == Constants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onFailure(failResponse("error"))
                    Log.e("onFailure", response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    /**
     * Verifica se existe conexão com internet
     */
    fun isConnectionAvailable(): Boolean {
        var result = false

        // Gerenciador de conexão
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Verifica versão do sistema rodando a aplicação
        val activeNet = cm.activeNetwork ?: return false
        val netWorkCapabilities = cm.getNetworkCapabilities(activeNet) ?: return false

        result = when {
            netWorkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            netWorkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }

        return result
    }
}