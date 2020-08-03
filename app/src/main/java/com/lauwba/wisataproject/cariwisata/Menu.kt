package com.lauwba.wisataproject.cariwisata

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lauwba.wisataproject.Config
import com.lauwba.wisataproject.R
import com.lauwba.wisataproject.RequestHandler
import org.json.JSONArray
import org.json.JSONObject

class Menu : AppCompatActivity(), RecyclerTouchListener.ClickListener {


    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var wisatas: ArrayList<WisataModel>
    lateinit var wisataModel: WisataModel
    lateinit var adapter: WisataAdapter
    lateinit var pd: ProgressDialog
    lateinit var fieldCari: EditText
    lateinit var cari: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        recyclerView = findViewById(R.id.listWisata)
        fieldCari = findViewById(R.id.cari_wisata)
        cari = findViewById(R.id.cari)

        linearLayoutManager = LinearLayoutManager(this@Menu)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager

        wisatas = ArrayList()
        var berita = getWisata()
        berita.execute()


        recyclerView.addOnItemTouchListener(RecyclerTouchListener(this@Menu, recyclerView, this))

        cari.setOnClickListener {
            cariWisata().execute(fieldCari.text.toString())
        }
    }

    override fun onLongClick(view: View?, position: Int) {

    }

    override fun onClick(view: View, position: Int) {
        var id: TextView
        id = view.findViewById(R.id.id_wisata)

        var intent: Intent = Intent(this@Menu, DetailWisata::class.java)
        intent.putExtra(Config.ID_WISATA, id.text.toString())
        startActivity(intent)

    }


    inner class getWisata : AsyncTask<String, String, String>() {
        var requestHandler: RequestHandler = RequestHandler()

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@Menu, "", "Sedang memuat", false, false)
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String = requestHandler.sendGetRequest(Config.URL_WISATA)

            return response
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.i("POST", result)
            pd.dismiss()
            try {
                var jsonObject = JSONObject(result)
                var content: JSONObject
                var jsonArray: JSONArray = jsonObject.getJSONArray(Config.DATA_ARRAY)
                if (jsonArray.length() > 0) {
                    for (i in 0..jsonArray.length()) {
                        content = jsonArray.getJSONObject(i)
                        wisataModel = WisataModel(
                            content.getString("id"),
                            content.getString("nama_wisata"),
                            content.getString("tanggal"),
                            content.getString("alamat")
                        )

                        wisatas.add(wisataModel)
                        adapter = WisataAdapter(this@Menu, wisatas)
                        recyclerView.adapter = adapter
                    }
                }
            } catch (e: Exception) {

            }
        }


    }

    inner class cariWisata : AsyncTask<String, String, String>() {

        var requestHandler: RequestHandler = RequestHandler()

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@Menu, "", "Sedang Mencari...", false, false)
        }

        override fun doInBackground(vararg params: String?): String {
            var response: String = requestHandler.sendGetRequest(Config.URL_CARI_WISATA + params[0])
            Log.i("TSG", Config.URL_CARI_WISATA + params[0])
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd.dismiss()
            try {
                var jsonObject = JSONObject(result)
                var content: JSONObject
                var jsonArray: JSONArray = jsonObject.getJSONArray(Config.DATA_ARRAY)
                if (jsonArray.length() > 0) {
                    wisatas.clear()
                    adapter = WisataAdapter(this@Menu, wisatas)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                    for (i in 0..jsonArray.length()) {
                        content = jsonArray.getJSONObject(i)
                        wisataModel = WisataModel(
                            content.getString("id"),
                            content.getString("nama_wisata"),
                            content.getString("tanggal"),
                            content.getString("alamat")
                        )

                        wisatas.add(wisataModel)
                        adapter = WisataAdapter(this@Menu, wisatas)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {

            }
        }
    }


}
