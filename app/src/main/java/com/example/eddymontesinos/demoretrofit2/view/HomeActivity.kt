package com.example.eddymontesinos.demoretrofit2.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.eddymontesinos.demoretrofit2.R
import com.example.eddymontesinos.demoretrofit2.adapter.ListaProductoAdapter
import com.example.eddymontesinos.demoretrofit2.api.ProductoService
import com.example.eddymontesinos.demoretrofit2.model.Producto
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener{


    var productoAdapter : ListaProductoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        ajusteToolbarHome()

        recyclerViewHome()
    }

    override fun onResume() {
        super.onResume()
     obtenerListaproductos()

    }

    fun obtenerListaproductos(bucador : String = ""){

        val listaCallback = ProductoService.create().obtenerProductos(bucador)
        listaCallback.enqueue(object : Callback<ArrayList<Producto>>{
            override fun onResponse(call: Call<ArrayList<Producto>>?, response: Response<ArrayList<Producto>>?) {
                if (response?.code() == 200) {

                    my_recyclerview.visibility = View.VISIBLE
                    pgCargando.visibility = View.GONE

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

    private fun recyclerViewHome(){

        productoAdapter = ListaProductoAdapter()


        productoAdapter?.onDetalleProductoClick ={

            val intent = Intent(this@HomeActivity,DetalleProductoActivity::class.java)
            intent.putExtra(DetalleProductoActivity.PRODUCTO_PARAM, it)
            startActivity(intent)
        }

        my_recyclerview.layoutManager = LinearLayoutManager(this)
        my_recyclerview.adapter = productoAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val item = menu!!.findItem(R.id.item_buscador)
        val buscador = item.actionView as SearchView
        buscador.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.option_agregar ->{
                startActivity<FormularioProductoActivity>()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.v("onQueryTextSubmit", "query = $query")
        obtenerListaproductos(query.toString())

        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


}
