package com.lauwba.wisataproject

import android.app.ProgressDialog
import android.content.ContextWrapper
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauwba.wisataproject.gallery.GalleryAdapter
import com.lauwba.wisataproject.gallery.GalleryModel
import com.lauwba.wisataproject.network.NetworkModule
import com.lauwba.wisataproject.saldo.IsiSaldo
import com.lauwba.wisataproject.saldo.ResponseSaldo
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.menu_saldo.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class Home : Fragment() {

    private var list: MutableList<GalleryModel>? = null
    private var pd: ProgressDialog? = null
//    , View.OnClickListener
//    override fun onClick(v: View?) {
//        when (v!!.id) {
//            R.id.btnwisatalaam -> {
//                var i: Intent
//                i = Intent(activity, Menu::class.java)
//                activity?.startActivity(i)
//            }
//            R.id.btnbooking -> {
//                var i: Intent
//                i = Intent(activity, Menu::class.java)
//                activity?.startActivity(i)
//            }
//        }
//    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        activity?.title = getString(R.string.title_beranda)
        val view = inflater.inflate(R.layout.home, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSaldo("FSSnC6UKtwXtrzgDf8nozC8pHhYZfluoYHrt3v4ixdQuZBBRo5")

        list = mutableListOf()
        get_data_gallery().execute()

        namapengguna.text =
            activity?.getSharedPreferences(Constant.PREFS_NAME, ContextWrapper.MODE_PRIVATE)
                ?.getString(Constant.NAMA, "not set")

        btnwisatalaam.setOnClickListener {
            var i: Intent
            i = Intent(activity, Menu::class.java)
            activity?.startActivity(i)
        }

        cvisisaldo.setOnClickListener {
            var i: Intent
            i = Intent(activity, IsiSaldo::class.java)
            activity?.startActivity(i)
        }

    }

    inner class get_data_gallery : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(activity, "", "Wait", true, true)
        }

        override fun doInBackground(vararg params: String?): String {

            val handler = RequestHandler()
            val result =
                handler.sendGetRequest(Config.url_gallery) //"http://192.168.43.93/newss/index.php/Webservice/select_berita"
            Log.d("String", result)
            return result
        }

        override fun onPostExecute(result: String?) {
            val objek = JSONObject(result)
            val array = objek.getJSONArray("data")
            for (i in 0 until array.length()) {
                val data = array.getJSONObject(i)
                val model = GalleryModel()
                model.id_foto = data.getString("id_foto")
                model.foto = data.getString("foto")
                list?.add(model)
                val adapter = list?.let {
                    GalleryAdapter(
                        it,
                        requireActivity()//buat dulu adpaterny
                    )
                }
//                rvgallery.layoutManager = LinearLayoutManager(this@Gallery)
//                rvgallery.adapter = adapter
//                val linearLayoutManager = LinearLayoutManager(this@Gallery)
                rvgallery.layoutManager = LinearLayoutManager(activity)
                rvgallery.adapter = adapter
                (rvgallery.layoutManager as LinearLayoutManager).orientation =
                    LinearLayoutManager.HORIZONTAL
//                recyclerView.setLayoutManager(linearLayoutManager)

            }
            super.onPostExecute(result)
            pd?.dismiss()

        }
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
