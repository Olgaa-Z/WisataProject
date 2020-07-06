package com.lauwba.wisataproject.saldo

import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.lauwba.wisataproject.Constant
import com.lauwba.wisataproject.R
import com.lauwba.wisataproject.network.NetworkModule
import com.lauwba.wisataproject.service.NotificationHandler
import com.lauwba.wisataproject.wisata.ResponseUpload
import kotlinx.android.synthetic.main.activity_isi_saldo.*
import me.abhinay.input.CurrencySymbols
import retrofit2.Call
import retrofit2.Response
import java.text.DecimalFormat
import java.util.concurrent.ThreadLocalRandom

class IsiSaldo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_isi_saldo)

        nominaltransfer.setCurrency(CurrencySymbols.INDONESIA)
        nominaltransfer.setDecimals(false)
        nominaltransfer.setDelimiter(true)
        nominaltransfer.setSeparator(".")

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("TOKEN", it.token)
        }

        //ini tarok di activity apapun terserah, yang penting udah ada proses subcribe.
        FirebaseMessaging.getInstance().subscribeToTopic("admin").addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Topik Berhasil disubscribe", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Topik Gagal disubscribe", Toast.LENGTH_SHORT).show()
            }
        }

        //get device token

        proses.setOnClickListener {

            val formatter = DecimalFormat("#,###")
            val kodeUnik = ThreadLocalRandom.current().nextInt(100, 999)
            val cleanNominal = nominaltransfer.cleanIntValue
            val totalRequestSaldo = cleanNominal.plus(kodeUnik)
            val email = getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
                ?.getString(Constant.EMAIL, "not set") //get from shared preferences

            if (nominaltransfer.text?.isEmpty() == true) {
                Toast.makeText(
                    this@IsiSaldo,
                    "Silahkan Isi Nominal Request Saldo Terlebih Dahulu",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sendRequestSaldo(email.toString(), kodeUnik, totalRequestSaldo)
            }
        }
        //ini contoh untuk ambil alamat
//        getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
//            .getString(Constant.ALAMAT, "not set")

//        jumlahsaldo.text= getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
//            .getString(Constant.JUMLAH_SALDO, "not set")

    }

    private fun sendRequestSaldo(email: String, kodeUnik: Int, totalRequestSaldo: Int) {
        NetworkModule.getService()
            .doRequest(email = email, kodeUnik = kodeUnik, nominal = totalRequestSaldo)
            .enqueue(object : retrofit2.Callback<ResponseUpload> {
                override fun onFailure(call: Call<ResponseUpload>, t: Throwable) {
                    Toast.makeText(this@IsiSaldo, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseUpload>,
                    response: Response<ResponseUpload>
                ) {
                    if (response.isSuccessful) {
                        val code = response.body()?.code
                        if (code == "200")
                            Toast.makeText(
                                this@IsiSaldo,
                                "Permintaan telah dikirimkan. Silahkan tunggu konfirmasi dari admin",
                                Toast.LENGTH_SHORT
                            ).show()

                        //send a notifications
                        val notificationItem = NotificationHandler.NotificationItem()
                        notificationItem.body =
                            "Permintaan pengisian saldo sebesar $totalRequestSaldo ke $email"
                        notificationItem.title = "Permintaan Pengisian Saldo"
//                        notificationItem.image =""

                        //notification model
                        val notificationFcmModel = NotificationHandler.NotificationFcmModel()
                        //yg ini unuk admin, ngepush notif ke user yang request saldo
//                        notificationFcmModel.to =
//                            "dNB980YOLIM:APA91bFU2MEFoYNwGOZODlEHgVDvfV8xkms0cNNDC6aOSXOnz6oqjcfOgYaHQ4ZqS9L_lbCBUSjJUkzc3OVr_u-38_nlEibDKulHB9m03FKq1CUjwMCHHK-IMYF2Sj3YWERjkf1djPkW"
                        notificationFcmModel.to =
                            "/topics/admin" /*to yg ini kalau untuk request saldo*/
                        notificationFcmModel.notification = notificationItem

                        //send the notifications
                        sendNotification(notificationFcmModel)

                    } else {
                        Toast.makeText(this@IsiSaldo, response.message(), Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun sendNotification(notificationFcmModel: NotificationHandler.NotificationFcmModel) {
        NetworkModule.getFcmService().sendANotification(notificationFcmModel)
            .enqueue(object : retrofit2.Callback<okhttp3.ResponseBody> {
                override fun onFailure(call: Call<okhttp3.ResponseBody>, t: Throwable) {
                    Toast.makeText(this@IsiSaldo, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<okhttp3.ResponseBody>,
                    response: Response<okhttp3.ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@IsiSaldo, "Berhasil",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@IsiSaldo, "Gagal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }


}
