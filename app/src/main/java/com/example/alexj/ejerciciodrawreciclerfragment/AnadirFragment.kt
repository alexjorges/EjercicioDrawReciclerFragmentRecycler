package com.example.alexj.ejerciciodrawreciclerfragment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_anadir.*
import android.os.Handler


class AnadirFragment : Fragment(), com.android.volley.Response.Listener<String>,com.android.volley.Response.ErrorListener {

    private lateinit var request: RequestQueue
    private lateinit var stringRequest: StringRequest


    override fun onErrorResponse(error: VolleyError?) {

        var progressDialog: ProgressDialog = ProgressDialog(context)
        progressDialog.setTitle("Añadiendo")
        progressDialog.setMessage("Añadiendo a la base de datos...")
        progressDialog.show()

        Handler().postDelayed({progressDialog.dismiss()},3000)

        Handler().postDelayed(
            {Toast.makeText(context,"ERROR, no se ha podido añadir",Toast.LENGTH_LONG).show()},2000)
    }

    override fun onResponse(response: String?) {
        var progressDialog: ProgressDialog
        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Añadiendo")
        progressDialog.setMessage("Añadiendo a la base de datos...")
        progressDialog.show()


        Handler().postDelayed({progressDialog.dismiss()},3000)

        Handler().postDelayed(
            {Toast.makeText(context,"Se ha registrado correctamente",Toast.LENGTH_LONG).show()},2000)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        // se crea un view y ese view se infla con la vista correspondiente
        var view: View
        view = inflater.inflate(R.layout.fragment_anadir,null)


        request = Volley.newRequestQueue(context)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btAnadir.setOnClickListener() {
            if (etCodigo.text.isEmpty() || etMarca.text.isEmpty() || etModelo.text.isEmpty()) {
                Toast.makeText(context, "No puede haber ningún campo vacio", Toast.LENGTH_LONG).show()
            } else {
                cargarWebService()
            }
        }
    }

    private fun cargarWebService()
    {
        var url: String = "http://iesayala.ddns.net/alexjorges/insert.php?codigo="+etCodigo.text.toString()+
                "&marca="+etMarca.text.toString()+
                "&modelo="+etModelo.text.toString()


        // con esto mandamos la informacion a Volley para que la lea y procece la informacion que queremos enviar
        stringRequest = StringRequest(com.android.volley.Request.Method.GET,url,this,this)


        // Mandandole al objeto request el objeto jsonObjectRequest
        //nos permite establecer la conexion entre los métodos onErrorResponse y onResponse
        request?.add(stringRequest)

    }
}





