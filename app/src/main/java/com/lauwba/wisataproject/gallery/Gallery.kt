package com.lauwba.wisataproject.gallery

import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lauwba.wisataproject.Config
import com.lauwba.wisataproject.R
import com.lauwba.wisataproject.RequestHandler
import kotlinx.android.synthetic.main.activity_gallery.*
import org.json.JSONObject

class Gallery : AppCompatActivity() {

    private var list: MutableList<GalleryModel>? = null
    private var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        list = mutableListOf()
        get_data_gallery().execute()
    }


    inner class get_data_gallery : AsyncTask<String, Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pd = ProgressDialog.show(this@Gallery, "", "Wait", true, true)
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
                        this@Gallery//buat dulu adpaterny
                    )
                }
                rvgallery.layoutManager = LinearLayoutManager(this@Gallery)
                rvgallery.adapter = adapter
            }
            super.onPostExecute(result)
            pd?.dismiss()

        }
    }
}
