package com.lauwba.wisataproject.content

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
import com.lauwba.wisataproject.R
import com.lauwba.wisataproject.RequestHandler
import org.json.JSONArray
import org.json.JSONObject

class Content : AppCompatActivity(), RecyclerTouchListener.ClickListener {


    lateinit var recyclerView: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var beritas: ArrayList<BeritaModel>
    lateinit var beritaModel: BeritaModel
    lateinit var adapter: BeritaAdapter
    lateinit var pd: ProgressDialog
    lateinit var fieldCari: EditText
    lateinit var cari: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        recyclerView = findViewById(R.id.listBerita)
        fieldCari = findViewById(R.id.cari_berita)
        cari = findViewById(R.id.cari)



        linearLayoutManager = LinearLayoutManager(this@Content)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager

        beritas = ArrayList()
        var berita = getBerita()
        berita.execute()


        recyclerView.addOnItemTouchListener(RecyclerTouchListener(this@Content, recyclerView, this))


        cari.setOnClickListener {
            cariBerita().execute(fieldCari.text.toString())
        }
    }

    override fun onLongClick(view: View?, position: Int) {

    }

    override fun onClick(view: View, position: Int) {
        var id: TextView
        id = view.findViewById(R.id.id_berita)

        var intent: Intent = Intent(this@Content, DetailBerita::class.java)
        intent.putExtra(ConfigApp.ID_BERITA, id.text.toString())
        startActivity(intent)

    }

    inner class getBerita : AsyncTask<String, String, String>() {
        var requestHandler: RequestHandler = RequestHandler()

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@Content, "", "Sedang memuat", false, false)
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String = requestHandler.sendGetRequest(ConfigApp.URL_BERITA_)

            return response
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            Log.i("POST", result)
            pd.dismiss()
            try {
                var jsonObject = JSONObject(result)
                var content: JSONObject
                var jsonArray: JSONArray = jsonObject.getJSONArray(ConfigApp.DATA_ARRAY)
                if (jsonArray.length() > 0) {
                    for (i in 0..jsonArray.length()) {
                        content = jsonArray.getJSONObject(i)
                        beritaModel = BeritaModel(
                            content.getString("id"),
                            content.getString("gambar"),
                            content.getString("judul"),
                            content.getString("isi_berita"),
                            content.getString("tgl_berita")
                        )

                        beritas.add(beritaModel)
                        adapter = BeritaAdapter(this@Content, beritas)
                        recyclerView.adapter = adapter
                    }
                }
            } catch (e: Exception) {

            }
        }


    }

    inner class cariBerita : AsyncTask<String, String, String>() {

        var requestHandler: RequestHandler = RequestHandler()

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@Content, "", "Sedang Mencari...", false, false)
        }

        override fun doInBackground(vararg params: String?): String {
            var response: String =
                requestHandler.sendGetRequest(ConfigApp.URL_CARI_BERITA + params[0])
            Log.i("TSG", ConfigApp.URL_CARI_BERITA + params[0])
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            pd.dismiss()
            try {
                var jsonObject = JSONObject(result)
                var content: JSONObject
                var jsonArray: JSONArray = jsonObject.getJSONArray(ConfigApp.DATA_ARRAY)
                if (jsonArray.length() > 0) {
                    beritas.clear()
                    adapter = BeritaAdapter(this@Content, beritas)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                    for (i in 0..jsonArray.length()) {
                        content = jsonArray.getJSONObject(i)
                        beritaModel = BeritaModel(
                            content.getString("id"),
                            content.getString("gambar"),
                            content.getString("judul"),
                            content.getString("isi_berita"),
                            content.getString("tgl_berita")
                        )

                        beritas.add(beritaModel)
                        adapter = BeritaAdapter(this@Content, beritas)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {

            }
        }
    }


}