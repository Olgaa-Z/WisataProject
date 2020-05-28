package com.lauwba.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.welcome.*

class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

//        btnStartAnotherActivity.setOnClickListener {
////            val intent = Intent(this, AnotherActivity::class.java)
////            // start your next activity
////            startActivity(intent)

        masuk.setOnClickListener {
            val intent = Intent (this, PhoneAuth::class.java)
            startActivity(intent)
        }

        daftar.setOnClickListener {
            val intent= Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}
