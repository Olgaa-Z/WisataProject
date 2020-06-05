package com.lauwba.wisataproject

import android.app.AlertDialog
import android.content.ContextWrapper
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import com.lauwba.wisataproject.datamodel.user.DataItemUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var toolbar: Toolbar
    lateinit var fav: MenuItem
    private var dataItemUser: DataItemUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        toolbars = findViewById(R.id.toolbars)

//        toolbars.title = ""
//        setSupportActionBar(toolbars)
        if (!getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE).contains(
                Constant.NO_TELP
            )
        ) {
            dataItemUser = intent.getSerializableExtra("data") as DataItemUser
            if (dataItemUser != null) {
                getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
                    .edit {
                        putString(Constant.NAMA, dataItemUser?.nama)
                        putString(Constant.ALAMAT, dataItemUser?.alamat)
                        putString(Constant.NO_TELP, dataItemUser?.notelp)
                        putString(Constant.EMAIL, dataItemUser?.email)
                    }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.coordinatorLayout, Akun())
                    .commit()
                bottom.selectedItemId = R.id.navigation_akun
            }
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.coordinatorLayout, Home())
                .commit()
        }

        //cara ambil value dari session/sharedpref
        //ini contoh untuk ambil alamat
//        getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
//            .getString(Constant.ALAMAT, "not set")

        //cara logout
//        getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
//            .edit {
//                clear()
//            }


        bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                com.lauwba.wisataproject.R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.coordinatorLayout, Home())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.lauwba.wisataproject.R.id.navigation_pesanan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.coordinatorLayout, Pesanan())
                        .commit()
//                    intent = Intent(applicationContext, Help::class.java)
//                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }

                com.lauwba.wisataproject.R.id.navigation_inbox -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.coordinatorLayout, Inbox())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                com.lauwba.wisataproject.R.id.navigation_akun -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.coordinatorLayout, Akun())
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
        when (item.itemId) {
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
