package com.lauwba.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_verification_auth.*

class VerificationAuth : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_auth)

        val verificationId = intent.getStringExtra("verificationId")

        buttonverification.setOnClickListener {
            if (verification.text.toString().isEmpty()) {

            } else {
                val credential = PhoneAuthProvider.getCredential(
                    verificationId,
                    verification.text.toString()
                )

                PhoneAuth().mAuth.signInWithCredential(credential).addOnSuccessListener {
//                    Toast.makeText(this@VerificationAuth, "Verifikasi Berhasil   ", Toast.LENGTH_SHORT).show()
                    Intent(this@VerificationAuth, MainActivity::class.java).apply {
                        startActivity(this)
                    }

                }.addOnFailureListener{
                    Toast.makeText(this@VerificationAuth,"Verifikasi Gagal", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
