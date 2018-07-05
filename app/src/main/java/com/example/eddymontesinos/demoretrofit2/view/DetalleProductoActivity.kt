package com.example.eddymontesinos.demoretrofit2.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.eddymontesinos.demoretrofit2.R
import com.example.eddymontesinos.demoretrofit2.api.ProductoService
import com.example.eddymontesinos.demoretrofit2.model.Producto
import kotlinx.android.synthetic.main.activity_detalle_producto.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetalleProductoActivity : AppCompatActivity() {

    companion object {
        const val PRODUCTO_PARAM = "producto"
        const val PRODCUTO_ACTULIZAR = "productoactulizar"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        ajusteToolbarDetalle()

        val detallesProducto = intent.getParcelableExtra<Producto>(PRODUCTO_PARAM)
        nombre_producto_detalle.text = detallesProducto.nombre
        precio_producto_detalle.text = "$/ "+detallesProducto.precio.toString()
        stock_detalle_producto.text = detallesProducto.stock.toString()
        lote_producto_detalle.text = detallesProducto.lote.toString()
        detalle_producto_detalle.text = detallesProducto.descripcion

        button_eliminar.setOnClickListener{

            val dialogBuilder = AlertDialog.Builder(this@DetalleProductoActivity)
            dialogBuilder.setTitle("Eliminar")
            dialogBuilder.setPositiveButton("SI",{dialog, which ->

                val eliminarproductoCallback = ProductoService.create().eliminar(detallesProducto.id!!.toInt())
                eliminarproductoCallback.enqueue(object :Callback<Producto>{
                    override fun onResponse(call: Call<Producto>?, response: Response<Producto>?) {
                        toast("Ocurrio Un Error al Eliminar prroducto")
                    }

                    override fun onFailure(call: Call<Producto>?, t: Throwable?) {

                    }
                })

                toast("Producto Eliminado")
                finish()
            })

            dialogBuilder.setNegativeButton("NO",{dialog, which ->

            })

           dialogBuilder.show()

        }

        button_actualizar.setOnClickListener{

            val intent = Intent(this@DetalleProductoActivity,FormularioProductoActivity::class.java)
            intent.putExtra(PRODCUTO_ACTULIZAR,detallesProducto)
            startActivity(intent)
        }

    }


    fun ajusteToolbarDetalle(){
        setSupportActionBar(detalleToolbar)
        title = "DETALLE DE PRODUCTO"
        detalleToolbar.navigationIcon = getDrawable(R.drawable.ic_atras)
        detalleToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

}
