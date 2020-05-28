package com.lauwba.wisataproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lauwba.wisataproject.datamodel.listbank.DataItemBank
import com.lauwba.wisataproject.datamodel.mutasi.ResponseMutasi
import kotlinx.android.synthetic.main.activity_adapter_listbank.view.*
import kotlinx.android.synthetic.main.activity_adapter_mutasi.view.*

class AdapterMutasi (private val mListMutasi: List<ResponseMutasi?>?) : RecyclerView.Adapter<AdapterMutasi.ViewHolder>()  {

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        private val Keterangan = itemView.keterangan
        private val Nominal = itemView.nominal
        private val SaldoAkhir = itemView.saldoakhir

        fun onBindItem (item : ResponseMutasi?){
//            itemView.setOnClickListener {
//                click(item)
//            }

            Keterangan.text=item?.description
            Nominal.text=item?.amount.toString()
            SaldoAkhir.text=item?.balance.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMutasi.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_adapter_mutasi,parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return mListMutasi?.size ?: 0
    }

    override fun onBindViewHolder(holder: AdapterMutasi.ViewHolder, position: Int) {
        holder.onBindItem(mListMutasi?.get(position))
    }
}
