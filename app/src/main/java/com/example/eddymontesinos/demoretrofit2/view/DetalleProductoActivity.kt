package com.example.eddymontesinos.demoretrofit2.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.eddymontesinos.demoretrofit2.R
import com.example.eddymontesinos.demoretrofit2.model.Producto
import kotlinx.android.synthetic.main.activity_detalle_producto.*


class DetalleProductoActivity : AppCompatActivity() {

    companion object {
        const val PRODUCTO_PARAM = "producto"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)

        ajusteToolbarDetalle()

       cargandoDetalleProducto()

    }


    fun ajusteToolbarDetalle(){
        setSupportActionBar(detalleToolbar)
        title = "DETALLE DE PRODUCTO"
        detalleToolbar.navigationIcon = getDrawable(R.drawable.ic_atras)
        detalleToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun cargandoDetalleProducto(){
        val detallesProducto = intent.getParcelableExtra<Producto>(PRODUCTO_PARAM)
        nombre_producto_detalle.text = detallesProducto.nombre
        precio_producto_detalle.text = "$/ "+detallesProducto.precio.toString()
        stock_detalle_producto.text = detallesProducto.stock.toString()
        lote_producto_detalle.text = detallesProducto.lote.toString()
        detalle_producto_detalle.text = detallesProducto.descripcion
    }
}
