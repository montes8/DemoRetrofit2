package com.example.eddymontesinos.demoretrofit2.api

import com.example.eddymontesinos.demoretrofit2.model.Producto
import retrofit2.Call
import retrofit2.http.*

interface ProductoService {


    @GET("productos")
    fun obtenerProductos(@Query("buscar") buscarParam: String): Call<ArrayList<Producto>>

    @GET("productos/{idProducto}")
    fun obtenerProductoSegunId(@Path("idProducto") idProducto: Int): Call<Producto>

    @POST("productos")
    fun registrar(@Body producto: Producto): Call<Void>

    @PUT("productos/{idProducto}")
    fun actualizar(@Path("idProducto") idProducto: Int,
                   @Body producto: Producto): Call<Void>

    @DELETE("productos/{idProducto}")
    fun eliminar(@Path("idProducto") idProducto: Int): Call<Producto>

    companion object {

        fun create(): ProductoService {
            return RetrofitCreator
                    .obtenerInstanciaRetrofit()
                    .create(ProductoService::class.java)
        }
    }
}