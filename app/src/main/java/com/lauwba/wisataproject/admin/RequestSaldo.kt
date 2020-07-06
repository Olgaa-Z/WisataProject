package com.lauwba.wisataproject.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauwba.wisataproject.R
import com.lauwba.wisataproject.network.NetworkModule
import kotlinx.android.synthetic.main.activity_request_saldo.*
import retrofit2.Call
import retrofit2.Response

class RequestSaldo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_saldo)

        getList()
    }

    private fun getList() {
        NetworkModule.getService().requestSaldoList()
            .enqueue(object : retrofit2.Callback<ResponseRequestSaldo> {
                override fun onFailure(call: Call<ResponseRequestSaldo>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<ResponseRequestSaldo>,
                    response: Response<ResponseRequestSaldo>
                ) {
                    if (response.isSuccessful) {
                        val code = response.body()?.code
                        if (code == 200) {
                            val data = response.body()?.data
                            val adapter = RequestSaldoAdapter(data, this@RequestSaldo)
                            rc.layoutManager = LinearLayoutManager(this@RequestSaldo)
                            rc.itemAnimator = DefaultItemAnimator()
                            rc.adapter = adapter
                            adapter.notifyDataSetChanged()
                        } else {

                        }
                    }
                }
            })
    }
}
