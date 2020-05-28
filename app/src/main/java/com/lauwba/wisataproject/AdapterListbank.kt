package com.lauwba.wisataproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lauwba.wisataproject.datamodel.listbank.DataItemBank
import kotlinx.android.synthetic.main.activity_adapter_listbank.view.*

class AdapterListbank(private val mListBank: List<DataItemBank?>?, private val click : (DataItemBank?)-> Unit) : RecyclerView.Adapter<AdapterListbank.ViewHolder>() {

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        private val namaBank = itemView.namabank
        private val gambarBank = itemView.gambarbank

        fun onBindItem (item : DataItemBank?){
            itemView.setOnClickListener {
                click(item)
            }

            namaBank.text=item?.label
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_adapter_listbank,parent, false
            )
        )
    }


    override fun getItemCount(): Int {
        return mListBank?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindItem(mListBank?.get(position))
    }







}
