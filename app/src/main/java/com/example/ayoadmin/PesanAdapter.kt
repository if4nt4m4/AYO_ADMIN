package com.example.ayoadmin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

class PesanAdapter(private val pesanList: MutableList<Pesan>): RecyclerView.Adapter<PesanAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesanAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_pemesanan,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addPesan(pesan: Pesan){
        pesanList.add(pesan)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNo: TextView = itemView.findViewById(R.id.tv_no)
        val tvNamaPemesan: TextView = itemView.findViewById(R.id.tv_namalngkp)
        val tvNoHP: TextView = itemView.findViewById(R.id.tv_nohpuser)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_emailuser)
        val tvUmur: TextView = itemView.findViewById(R.id.tv_umuruser)
    }

    override fun onBindViewHolder(holder: PesanAdapter.ViewHolder, position: Int) {
        val pesan = pesanList[position]
        holder.tvNo.text = pesan.No.toString()
        holder.tvNamaPemesan.text = pesan.Nama
        holder.tvNoHP.text = pesan.Nohp
        holder.tvEmail.text = pesan.Email
        holder.tvUmur.text = pesan.Umur.toString()
    }

    override fun getItemCount(): Int {
        return pesanList.size
    }

}