package com.example.alexj.ejerciciodrawreciclerfragment

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BorrarFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // llamamos al método creado abajo y le pasamos -1
        pantallas(-1)

    }



    // métodos que no se tocan, vienen asi
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }



    // método para enlazar el fragment correspondiente según se pulse en una opción
    fun pantallas(id: Int ){
        val fragment = when(id){
            R.id.ver_coches -> {
                ConsultarFragment()
            }
            R.id.añadir_coches ->{
                AnadirFragment()
            }
            R.id.Borrar_coches ->{
                BorrarFragment()
            }
            else ->{
                ConsultarFragment()
            }
        }

        // se añade esto pasandole el fragment de arriba, que cogerá un valor dependiendo de lo que se pulse
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.relativeLayout, fragment as Fragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        // Se llama al método creado antes si le pasa la identidad del item
        pantallas(item.itemId)


        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }



}



