package com.example.eddymontesinos.demoretrofit2.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eddymontesinos.demoretrofit2.R
import com.example.eddymontesinos.demoretrofit2.model.Producto
import kotlinx.android.synthetic.main.molde_lista_productos.view.*

class ListaProductoAdapter : RecyclerView.Adapter<ListaProductoAdapter.ProductoViewHolder>() {

    private var productos : List<Producto>? = null

    fun addList(productos : List<Producto>){
        this.productos = productos

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.molde_lista_productos,parent,false)
        return ProductoViewHolder(view)
    }

    override fun getItemCount(): Int {
        val checkedUser = checkNotNull(productos){return 0}
        return checkedUser.size
    }


    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos!![position]

        holder.nombreProducto.text = producto.nombre
        holder.precioproducto.text = "$/ "+producto.precio.toString()
        holder.itemView.setOnClickListener{
            

        }

    }

    class ProductoViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val nombreProducto = itemView.nomdre_producto
        val precioproducto = itemView.precio_producto



    }

}