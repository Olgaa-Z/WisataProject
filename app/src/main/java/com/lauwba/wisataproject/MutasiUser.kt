package com.lauwba.wisataproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import com.lauwba.wisataproject.datamodel.transaksisaldo.ResponseTransaksiSaldo
import com.lauwba.wisataproject.network.NetworkModule
import kotlinx.android.synthetic.main.activity_mutasi_user.*
import retrofit2.Call
import retrofit2.Response

class MutasiUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutasi_user)

        getMutasi("6285761990862",10)
    }

    private fun getMutasi(notelp: String, page: Int) {
        //?in ApiService Interface at bankId
        NetworkModule.getService().getTransaksiSaldo("6285761990862" , 10).enqueue(object:retrofit2.Callback<ResponseTransaksiSaldo?>{

            override fun onFailure(call: Call<ResponseTransaksiSaldo?>, t: Throwable) {
                Toast.makeText(this@MutasiUser, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseTransaksiSaldo?>,
                response: Response<ResponseTransaksiSaldo?>
            ){

                if(response.isSuccessful){
                    val data = response.body()?.history
                    val adapter = AdapterMutasiUser(data)

                    rvmutasiuser.itemAnimator = DefaultItemAnimator()
                    rvmutasiuser.adapter= adapter
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}
