package com.lauwba.wisataproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lauwba.wisataproject.datamodel.mutasi.ResponseMutasi
import com.lauwba.wisataproject.datamodel.transaksisaldo.HistoryItem
import com.lauwba.wisataproject.datamodel.transaksisaldo.ResponseTransaksiSaldo
import kotlinx.android.synthetic.main.activity_adapter_mutasi.view.*

class AdapterMutasiUser (private val mListMutasi: List<HistoryItem?>?) : RecyclerView.Adapter<AdapterMutasiUser.ViewHolder>() {

    inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        private val Keterangan = itemView.keterangan
        private val Nominal = itemView.nominal
        private val SaldoAkhir = itemView.saldoakhir

        fun onBindItem (item : HistoryItem?){
//            itemView.setOnClickListener {
//                click(item)
//            }

            Keterangan.text="${item?.description}\n${item?.date}"
            Nominal.text=item?.amount.toString()
            SaldoAkhir.text=item?.balance.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMutasiUser.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_adapter_mutasi,parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return mListMutasi?.size ?: 0
    }

    override fun onBindViewHolder(holder: AdapterMutasiUser.ViewHolder, position: Int) {
        holder.onBindItem(mListMutasi?.get(position))
    }
}
