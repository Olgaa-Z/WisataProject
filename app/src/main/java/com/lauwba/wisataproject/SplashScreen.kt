package com.lauwba.wisataproject

import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        handler = Handler()

        handler.postDelayed(Runnable {
            if (getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE).contains(
                    Constant.NO_TELP
                )
            ) {
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            } else {
                var intent = intent
                intent = Intent(this@SplashScreen, Welcome::class.java)
                startActivity(intent)
            }
            this@SplashScreen.finish()
        }, 2000)
    }
}
