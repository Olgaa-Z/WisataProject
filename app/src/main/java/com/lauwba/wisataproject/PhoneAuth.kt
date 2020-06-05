package com.lauwba.wisataproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.lauwba.wisataproject.datamodel.user.DataItemUser
import com.lauwba.wisataproject.datamodel.user.ResponseUser
import com.lauwba.wisataproject.network.NetworkModule
import com.vanillaplacepicker.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_phone_auth.*
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.TimeUnit

class PhoneAuth : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    private lateinit var verificationId: String
    private var mPhoneAuthCredential: PhoneAuthCredential? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)

        buttonsend.setOnClickListener {
            if (notelp.text.toString().isEmpty()) {

            } else {
                getDetailUser("${kodenegara.text}${notelp.text}")
            }
        }

    }

    private fun getDetailUser(notelp: String) {
        NetworkModule.getService().doLogin(notelp)
            .enqueue(object : retrofit2.Callback<ResponseUser> {
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    ToastUtils.showToast(this@PhoneAuth, t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.code == 200) {
                            doAuth(response.body()?.data)
                        } else {
                            ToastUtils.showToast(this@PhoneAuth, response.body()?.message)
                        }
                    } else {
                        ToastUtils.showToast(this@PhoneAuth, "Something wrong")
                    }
                }
            })
    }

    private fun doAuth(data: List<DataItemUser?>?) {
        val mNoTelp = "+${kodenegara.text}${notelp.text}"
        mAuth.setLanguageCode("id")
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mNoTelp,
            30,
            TimeUnit.SECONDS,
            this@PhoneAuth,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Log.d("SMS CODE", p0.smsCode.toString())
                    signInWithPhoneAuthCredential(p0, data)
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.d("onVerficationFailed", p0.message.toString())
                }

                override fun onCodeSent(
                    p0: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    verificationId = p0
                    Intent(this@PhoneAuth, VerificationAuth::class.java).apply {
                        data?.forEach {
                            putExtra("data", it)
                        }
                        putExtra("verificationId", verificationId)
                        startActivity(this)
                    }
                }
            }
        )
    }

    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        data: List<DataItemUser?>?
    ) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    ToastUtils.showToast(this@PhoneAuth, "Verifikasi Sukses")
                    Log.d("success", "signInWithCredential:success")
                    Intent(this@PhoneAuth, MainActivity::class.java).apply {
                        data?.forEach {
                            putExtra("data", it)
                        }
                        startActivity(this)
                    }
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("Failure", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }
    }
}
