package com.example.alexj.ejerciciodrawreciclerfragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lista_item.view.*

class cochesAdaptador(val coches: ArrayList<Coches>, val context: ConsultarFragment): RecyclerView.Adapter<cochesAdaptador.CochesHolder>() {

    // creamos un holder con los componentes gráficos y se lo pasamos a la clase arriba
    class CochesHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCodigo = view.tvCodigo
        val tvMarca = view.tvMarca
        val tvModelo = view.tvModelo
    }


    override fun onBindViewHolder(p0: CochesHolder, p1: Int) {
        p0?.tvCodigo?.text = coches.get(p1).codigo.toString()
        p0?.tvMarca?.text =  coches.get(p1).marca
        p0?.tvModelo?.text = coches.get(p1).modelo

    }


    override fun getItemCount(): Int {
        return coches.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CochesHolder {

        val layoutInflater = LayoutInflater.from(p0.context)
        val inflar = layoutInflater.inflate(R.layout.lista_item, p0, false)
        return CochesHolder(inflar)

    }


}