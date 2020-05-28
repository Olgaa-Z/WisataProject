package com.lauwba.wisataproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lauwba.wisataproject.network.NetworkModule
import com.lauwba.wisataproject.saldo.ResponseSaldoUser
import kotlinx.android.synthetic.main.toolbardua.*
import retrofit2.Call
import retrofit2.Response

class Pesanan : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.pesanan, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSaldouser("6285761990862")
    }

    private fun getSaldouser(key: String) {
        NetworkModule.getService().getSaldoUser(key)

            .enqueue(object : retrofit2.Callback<ResponseSaldoUser>{
                override fun onFailure(call: Call<ResponseSaldoUser>, t: Throwable) {
                    Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseSaldoUser>,
                    response: Response<ResponseSaldoUser>
                ){
                    if (response.isSuccessful){

                        val data = response.body()
                        saldouser.text=data?.saldo?.get(0)?.saldo.toString()

                    }
                }

            })
    }
}
