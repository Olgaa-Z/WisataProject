@file:Suppress("DEPRECATION")

package com.lauwba.wisataproject

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauwba.wisataproject.network.NetworkModule
import com.lauwba.wisataproject.wisata.ResponseUpload
import com.vanillaplacepicker.utils.ToastLength
import com.vanillaplacepicker.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_phone_auth.notelp
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response

class Register : AppCompatActivity() {

    private var pDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        buttonregister.setOnClickListener {
            if (notelp.text.toString().isEmpty()) {
                ToastUtils.showToast(this@Register, "Silahkan isi dulu", ToastLength.Long)
            } else {
                sendData(
                    kodenegara.text.toString() + notelp.text.toString(),
                    nama.text.toString(),
                    email.text.toString(),
                    alamat.text.toString()
                )
            }
        }

    }

    private fun sendData(notelp: String, nama: String, email: String, alamat: String) {
        NetworkModule.getService()
            .register(noTelp = notelp, nama = nama, email = email, alamat = alamat)
            .enqueue(object : retrofit2.Callback<ResponseUpload> {
                override fun onFailure(call: Call<ResponseUpload>, t: Throwable) {
                    pDialog?.dismiss()
                    ToastUtils.showToast(this@Register, t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseUpload>,
                    response: Response<ResponseUpload>
                ) {
                    pDialog?.dismiss()
                    if (response.isSuccessful) {
                        if (response.body()?.code == "200") {
                            ToastUtils.showToast(this@Register, response.body()?.message)
                            finish()
                            Intent(this@Register, PhoneAuth::class.java).apply {
                                startActivity(this)
                            }
                        } else {
                            ToastUtils.showToast(this@Register, response.body()?.message)
                        }
                    } else {
                        ToastUtils.showToast(this@Register, response.errorBody().toString())
                    }
                }

            })
    }
}
