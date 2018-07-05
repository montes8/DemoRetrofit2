package com.example.eddymontesinos.demoretrofit2.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.eddymontesinos.demoretrofit2.R
import com.example.eddymontesinos.demoretrofit2.api.ProductoService
import com.example.eddymontesinos.demoretrofit2.model.Producto
import kotlinx.android.synthetic.main.activity_formulario_producto.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormularioProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_producto)

        ajustarToolbarGuardar()

        guardarproducto()



    }

    fun ajustarToolbarGuardar(){
        setSupportActionBar(formularioToolbar)
        title = "GUARDAR PRODUCTO"
        formularioToolbar.navigationIcon = getDrawable(R.drawable.ic_atras)
        formularioToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    fun guardarproducto(){

        val prodcutoActualisar=intent.getParcelableExtra<Producto>(DetalleProductoActivity.PRODCUTO_ACTULIZAR)

        if (prodcutoActualisar == null) {
                    button_guardar_producto.setOnClickListener {

                        val nombre = edit_text_nombre.text.toString()
                        val precio = edit_text_precio.text.toString().toDouble()
                        val lote = edit_lote.text.toString().toInt()
                        val stock = edit_stock.text.toString().toInt()
                        val descripcion = edit_text_descripcion.text.toString()

                        val producto = Producto(0, nombre, precio, lote, stock, descripcion)
                        val guardarProducto = ProductoService.create().registrar(producto)

                        guardarProducto.enqueue(object : Callback<Void> {

                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                toast("Producto Guardado")
                                edit_text_nombre.setText("")
                                edit_text_precio.setText("")
                                edit_lote.setText("")
                                edit_stock.setText("")
                                edit_text_descripcion.setText("")
                            }
                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                toast("Ocurrio un Error")
                            }
                        })
                    }

        }else{

            edit_text_nombre.setText(prodcutoActualisar.nombre)
            edit_text_precio.setText(prodcutoActualisar.precio.toString())
            edit_lote.setText(prodcutoActualisar.lote.toString())
            edit_stock.setText(prodcutoActualisar.stock.toString())
            edit_text_descripcion.setText(prodcutoActualisar.descripcion)
            val idactulisar = prodcutoActualisar.id

                    button_guardar_producto.setOnClickListener{

                        val nombreactulizar = edit_text_nombre.text.toString()
                        val precioactulaizar = edit_text_precio.text.toString().toDouble()
                        val loteactulizar = edit_lote.text.toString().toInt()
                        val stockactulizar = edit_stock.text.toString().toInt()
                        val descripcionactulizar = edit_text_descripcion.text.toString()

                        val productoactulizado = Producto(0, nombreactulizar, precioactulaizar, loteactulizar, stockactulizar, descripcionactulizar)
                        val actulizarproducto = ProductoService.create().actualizar(idactulisar!!,productoactulizado)
                        actulizarproducto.enqueue(object :Callback<Void>{
                            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                               toast("Producto Actulizado")
                            }
                            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                                toast("Ocurrio un Error")
                            }
                        })
                    }
        }
    }
}
