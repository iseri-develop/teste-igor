package com.example.testeaiko.service.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"
        private const val BASE_URL_2 = "https://aiko-olhovivo-proxy.aikodigital.io/"

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()

//            httpClient.addInterceptor { chain ->
//                val request = chain.request()
//                    .newBuilder()
//                    .build()
//                chain.proceed(request)
//            }

            // Adiciona um interceptor para adicionar o token na requisição
            if (!::INSTANCE.isInitialized) {
                synchronized(RetrofitClient::class) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL_2)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }

            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }

    }
}