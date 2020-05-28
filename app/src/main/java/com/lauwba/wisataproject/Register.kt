package com.lauwba.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_phone_auth.kodenegara
import kotlinx.android.synthetic.main.activity_phone_auth.notelp
import kotlinx.android.synthetic.main.activity_register.*
import java.util.concurrent.TimeUnit

class Register : AppCompatActivity() {

    val  mAuth = FirebaseAuth.getInstance()
    private lateinit var verificationId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        buttonregister.setOnClickListener {
            if (notelp.text.toString().isEmpty()){

            }else{
                val mNoTelp = "${kodenegara.text}${notelp.text}"
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    mNoTelp,
                    30,
                    TimeUnit.SECONDS,
                    this@Register,
                    object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                            Log.d("onVerficationCompleted", p0?.smsCode.toString())

                        }

                        override fun onVerificationFailed(p0: FirebaseException) {
                            Log.d("onVerficationFailed", p0?.message.toString())
                        }

                        override fun onCodeSent(
                            p0: String,
                            p1: PhoneAuthProvider.ForceResendingToken
                        ) {
//                            super.onCodeSent(p0, p1)
                            verificationId = p0
                            Toast.makeText(this@Register, "Kode telah dikirim", Toast.LENGTH_SHORT).show()

                            Intent(this@Register, VerificationAuth::class.java).apply {
                                this.putExtra("verificationId", verificationId)
                                this.putExtra("nama", nama.text.toString())
                                this.putExtra("email", email.text.toString())
                                this.putExtra("alamat", alamat.text.toString())
                                this.putExtra("nomortelepon", notelp.text.toString())
                                startActivity(this)
                            }
                        }
                    }
                )
            }
        }

    }
}
