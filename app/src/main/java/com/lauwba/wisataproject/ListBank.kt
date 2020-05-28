package com.lauwba.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauwba.wisataproject.datamodel.listbank.ResponseListBank
import com.lauwba.wisataproject.network.NetworkModule
import kotlinx.android.synthetic.main.activity_list_bank.*
import retrofit2.Call
import retrofit2.Response

class ListBank : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_bank)

        getListBank("FSSnC6UKtwXtrzgDf8nozC8pHhYZfluoYHrt3v4ixdQuZBBRo5")  //altenter
    }

    private fun getListBank(key: String) {
        NetworkModule.getService().getListBank(key)
            .enqueue(object : retrofit2.Callback<ResponseListBank>{

                override fun onFailure(call: Call<ResponseListBank>, t: Throwable) {
                    Toast.makeText(this@ListBank, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseListBank>,
                    response: Response<ResponseListBank>
                ) {
                    if (response.isSuccessful){
                        val listBank = response.body()?.data
                        if (listBank?.isEmpty()== true ){
                            Toast.makeText(this@ListBank,"Belum Ada Daftar Bank", Toast.LENGTH_SHORT).show()
                        }else {
                            val adapter = AdapterListbank(listBank){
                                // click
                                Intent(this@ListBank, Mutasi::class.java).apply{
                                    this.putExtra("data", it)
                                    startActivity(this)
                                }
                            }

                            rvlistbank.itemAnimator = DefaultItemAnimator()
                            rvlistbank.layoutManager = LinearLayoutManager(this@ListBank)
                            rvlistbank.adapter = adapter
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            })

    }
}
