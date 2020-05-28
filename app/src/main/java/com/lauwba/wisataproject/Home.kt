package com.lauwba.wisataproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauwba.wisataproject.datamodel.listbank.ResponseListBank
import com.lauwba.wisataproject.network.NetworkModule
import com.lauwba.wisataproject.saldo.ResponseSaldo
import kotlinx.android.synthetic.main.activity_list_bank.*
import kotlinx.android.synthetic.main.menu_saldo.*
import retrofit2.Call
import retrofit2.Response

class Home : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.home, container, false)
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSaldo("FSSnC6UKtwXtrzgDf8nozC8pHhYZfluoYHrt3v4ixdQuZBBRo5")
    }

    private fun getSaldo(key: String) {
        NetworkModule.getService().getsaldo(key)

            .enqueue(object : retrofit2.Callback<ResponseSaldo>{
                override fun onFailure(call: Call<ResponseSaldo>, t: Throwable) {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseSaldo>,
                    response: Response<ResponseSaldo>
                ){
                    if (response.isSuccessful){

                        val data = response.body()
                        saldo.text=data?.balance.toString()

                    }
                }

            })
    }
}
