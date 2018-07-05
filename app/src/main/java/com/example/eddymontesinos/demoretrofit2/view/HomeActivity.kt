package com.example.eddymontesinos.demoretrofit2.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.eddymontesinos.demoretrofit2.R
import com.example.eddymontesinos.demoretrofit2.adapter.ListaProductoAdapter
import com.example.eddymontesinos.demoretrofit2.api.ProductoService
import com.example.eddymontesinos.demoretrofit2.model.Producto
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    var productoAdapter : ListaProductoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ajusteToolbarHome()

        productoAdapter = ListaProductoAdapter()
        my_recyclerview.layoutManager = LinearLayoutManager(this)
        my_recyclerview.adapter = productoAdapter
        //LLAMAMOS AL SERVICI WEB
        val productoService = ProductoService.create()
        val listaCallback = productoService.obtenerProductos()
        listaCallback.enqueue(object : Callback<ArrayList<Producto>>{
            override fun onResponse(call: Call<ArrayList<Producto>>?, response: Response<ArrayList<Producto>>?) {
                if (response?.code() == 200) {
                    val listaproducto = response?.body()

                    if (listaproducto != null) {
                        productoAdapter!!.addList(listaproducto)
                    }
                }else{
                    Toast.makeText(this@HomeActivity, "Ocurrio un error al obtener la lista (${response?.code()})", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Producto>>?, t: Throwable?) {

                Toast.makeText(this@HomeActivity, "Ocurrio un error al obtener la lista", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun ajusteToolbarHome() {
        setSupportActionBar(homeToolbar)
        title = "LISTA PRODUCTOS"
    }
}
