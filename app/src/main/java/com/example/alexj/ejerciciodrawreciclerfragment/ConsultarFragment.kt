package com.example.alexj.ejerciciodrawreciclerfragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_anadir.*
import kotlinx.android.synthetic.main.lista_item.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlinx.android.synthetic.main.fragment_consultar.*
import android.R.attr.data
import android.util.Log.d
import com.example.alexj.ejerciciodrawreciclerfragment.R.id.message
import com.example.alexj.ejerciciodrawreciclerfragment.R.styleable.AlertDialog
import com.example.alexj.ejerciciodrawreciclerfragment.R.styleable.Snackbar


class ConsultarFragment : Fragment(), Response.Listener<JSONObject>, Response.ErrorListener{

    private lateinit var rvLista: RecyclerView
    private var coches: ArrayList<Coches> = ArrayList()
    private lateinit var coche: Coches
    private var url: String = "http://iesayala.ddns.net/alexjorges/coches2.php"

    private lateinit var progressBar: ProgressDialog

    lateinit var request: RequestQueue
    lateinit var jsonObjectRequest: JsonObjectRequest


    // metodos que hay que sobreescribir al implementar Response de Volley
    override fun onResponse(jsonObj: JSONObject) {

        // creamos un JSONArray
        var jsonArray: JSONArray = jsonObj.getJSONArray("coches")//en vez de opt get

        try {

            for (i in 0..(jsonArray.length() -1)){

                // se crea un objeto tipo JSONObject y le pasamos la posicion i
                var jsonObject: JSONObject = jsonArray.getJSONObject(i)


                //crear directamente el coche y pasarle los parametros

                    coche = Coches(
                        jsonObject.optInt("codigo"),
                        jsonObject.optString("marca"),
                        jsonObject.optString("modelo"))

                    coches.add (coche)

            }

            // Creamos el adaptador
            var adaptadorCoches: cochesAdaptador = cochesAdaptador(coches,this)

            //asignamos el adaptador a la lista
            rvLista.adapter= adaptadorCoches


        } catch (e: JSONException ){
                e.printStackTrace()
                Toast.makeText(this.context,"No se puedo establecer la conexión",Toast.LENGTH_LONG).show()
        }

    }

    override fun onErrorResponse(error: VolleyError?) {
        Toast.makeText(context,"Error al conectar",Toast.LENGTH_LONG).show()
        Log.e("Volley Error:", error.toString())
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // se crea un view y ese view se infla con el fragment correspondiente
        var vista: View
        vista = inflater?.inflate(R.layout.fragment_consultar, container, false)


        // Enlazamos el reciclerView
        rvLista = vista.findViewById(R.id.rvLista)

        // esto es dar la forma del linearLayout
        rvLista.layoutManager = LinearLayoutManager(context)


        cargarWebService()

        return vista
    }



    @RequiresApi(Build.VERSION_CODES.N)
    private fun cargarWebService() {

        var progressBar = ProgressDialog(context)
        progressBar.setTitle("Cargando...")
        progressBar.setMessage("cargando datos...espere por favor")
        progressBar.show()

        Handler().postDelayed({progressBar.dismiss()},3000)

        //var url: String = "http://iesayala.ddns.net/alexjorges/coches2.php"

        request = Volley.newRequestQueue(context)

        jsonObjectRequest =  JsonObjectRequest(Request.Method.GET,url,null,this,this)


        request.add(jsonObjectRequest)

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

}
