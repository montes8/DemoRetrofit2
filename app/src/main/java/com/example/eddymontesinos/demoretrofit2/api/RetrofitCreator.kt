package com.example.eddymontesinos.demoretrofit2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCreator {

    private val BASE_URL = "https://mantenimientogalaxy.herokuapp.com/api/"

    private var retrofit: Retrofit? = null

    fun obtenerInstanciaRetrofit(): Retrofit {
        if(retrofit ==null){
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}