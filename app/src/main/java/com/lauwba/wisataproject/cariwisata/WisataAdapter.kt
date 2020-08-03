package com.lauwba.wisataproject.cariwisata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lauwba.wisataproject.R

class WisataAdapter(context: Context, wisataList: ArrayList<WisataModel>) :
    RecyclerView.Adapter<WisataAdapter.ViewHolder>() {

    var context: Context = context
    var wisataList: ArrayList<WisataModel>

    init {
        this.wisataList = wisataList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v: View
        var layoutInflater: LayoutInflater

        layoutInflater = LayoutInflater.from(parent.context)
        v = layoutInflater.inflate(R.layout.activity_wisata_adapter, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return wisataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var wisata: WisataModel = wisataList.get(position)

        holder.id_wisata.text = wisata._id
        holder.namawisata.text = wisata._namawisata
        holder.tanggal.text = wisata._tanggal
        holder.alamatwisata.text = wisata._alamat
//        Glide
//            .with(context)
//            .load(Config.URL_GAMBAR + wisata._gambar)
//            .into(holder.gambar)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tanggal: TextView
        var namawisata: TextView
        var id_wisata: TextView
        var alamatwisata: TextView

        init {
            id_wisata = itemView.findViewById(R.id.id_wisata)
            namawisata = itemView.findViewById(R.id.namawisata)
            tanggal = itemView.findViewById(R.id.tanggal)
            alamatwisata = itemView.findViewById(R.id.alamatwisata)


        }
    }
}
