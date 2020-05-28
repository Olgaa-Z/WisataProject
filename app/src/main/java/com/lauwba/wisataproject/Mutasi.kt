package com.lauwba.wisataproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import com.lauwba.wisataproject.datamodel.listbank.DataItemBank
import com.lauwba.wisataproject.datamodel.listbank.ResponseListBank
import com.lauwba.wisataproject.datamodel.mutasi.ResponseMutasi
import com.lauwba.wisataproject.network.NetworkModule
import kotlinx.android.synthetic.main.activity_mutasi.*
import kotlinx.android.synthetic.main.akun.*
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Response

class Mutasi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutasi)

        val dataBank =intent.getSerializableExtra("data") as DataItemBank
        val bankId = dataBank.bankId

        //create function mutasi getMutasi AE
        getMutasi("FSSnC6UKtwXtrzgDf8nozC8pHhYZfluoYHrt3v4ixdQuZBBRo5", bankId, 10)
    }

    private fun getMutasi(key: String, bankId: String?, count: Int) {
        //?in ApiService Interface at bankId
        NetworkModule.getService().getMutasi(key, bankId, count).enqueue(object:retrofit2.Callback<List<ResponseMutasi?>>{

            override fun onFailure(call: Call<List<ResponseMutasi?>>, t: Throwable) {
                Toast.makeText(this@Mutasi, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<ResponseMutasi?>>,
                response: Response<List<ResponseMutasi?>>
            ){

                if(response.isSuccessful){
                    val data = response.body()
                    val adapter = AdapterMutasi(data)

                    rvmutasi.itemAnimator = DefaultItemAnimator()
                    rvmutasi.adapter= adapter
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}
