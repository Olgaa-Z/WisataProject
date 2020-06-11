package com.lauwba.wisataproject.gallery

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lauwba.wisataproject.Config
import com.lauwba.wisataproject.R
import kotlinx.android.synthetic.main.activity_gallery_adapter.view.*

class GalleryAdapter(private val list: MutableList<GalleryModel>, private val context: Context) :
    RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.activity_gallery_adapter, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.photogallery
        val relative: RelativeLayout = itemView.relative
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val item = list[p1]
        p0.relative.setOnClickListener {
            val intent = Intent(context, DetailGallery::class.java)
            intent.putExtra(Config.id, item.id_foto)
            intent.putExtra("from", "foto")
            context.startActivity(intent)

        }
        Glide.with(context).load(Config.url_gambar_gallery + item.foto).into(p0.image)
    }

}
