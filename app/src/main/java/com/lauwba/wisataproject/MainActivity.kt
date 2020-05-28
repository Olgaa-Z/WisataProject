package com.lauwba.wisataproject

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var toolbar: Toolbar
    lateinit var fav: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        toolbars = findViewById(R.id.toolbars)

//        toolbars.title = ""
//        setSupportActionBar(toolbars)

        supportFragmentManager.beginTransaction()
            .replace(com.lauwba.wisataproject.R.id.coordinatorLayout, Home())
            .commit()


        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                com.lauwba.wisataproject.R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.lauwba.wisataproject.R.id.coordinatorLayout, Home())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.lauwba.wisataproject.R.id.navigation_pesanan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.lauwba.wisataproject.R.id.coordinatorLayout, Pesanan())
                        .commit()
//                    intent = Intent(applicationContext, Help::class.java)
//                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                com.lauwba.wisataproject.R.id.navigation_inbox -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.lauwba.wisataproject.R.id.coordinatorLayout, Inbox())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.lauwba.wisataproject.R.id.navigation_akun -> {
                    supportFragmentManager.beginTransaction()
                        .replace(com.lauwba.wisataproject.R.id.coordinatorLayout, Akun())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }


            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflate = menuInflater
        menuInflate.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        intent = Intent(applicationContext, Cart::class.java)
        when(item.getItemId()){
            R.id.menu_settings -> {
                Toast.makeText(applicationContext, "Feature is locked", Toast.LENGTH_LONG).show()
            }

            R.id.menu_logout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Keluar dari Aplikasi?")
        builder.setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->
            //if user pressed "yes", then he is allowed to exit from application
            finish()
        })
        builder.setNegativeButton("Tidak", DialogInterface.OnClickListener { dialog, which ->
            //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        })
        val alert = builder.create()
        alert.show()
    }
}
