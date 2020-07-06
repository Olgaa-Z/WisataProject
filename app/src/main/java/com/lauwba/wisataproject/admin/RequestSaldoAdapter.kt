package com.lauwba.wisataproject.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lauwba.wisataproject.R
import kotlinx.android.synthetic.main.activity_request_saldo_adapter.view.*

class RequestSaldoAdapter(
    private val list: List<DataItemRequestSaldo?>?,
    private val context: Context
) : RecyclerView.Adapter<RequestSaldoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestSaldoAdapter.ViewHolder {
        val v = LayoutInflater.from(context)
            .inflate(R.layout.activity_request_saldo_adapter, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(p0: RequestSaldoAdapter.ViewHolder, p1: Int) {
        p0.onBindItem(list?.get(p1))

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nama = itemView.nama
        private val nominal = itemView.nominal
        private val kodeunik = itemView.kodeunik
        private val tanggal = itemView.tanggal

        fun onBindItem(item: DataItemRequestSaldo?) {
            nama.text = item?.nama
            kodeunik.text = item?.kodeUnik
            tanggal.text = item?.tanggal
            nominal.text = item?.nominal

            itemView.setOnClickListener {
                //update transaction in here
            }
        }

    }


}
