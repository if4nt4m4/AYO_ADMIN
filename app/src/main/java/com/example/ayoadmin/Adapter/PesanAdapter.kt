package com.example.ayoadmin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Models.Pesan
import com.example.ayoadmin.R

class PesanAdapter(private val context: Context, private val pemesanList: ArrayList<Pesan>): RecyclerView.Adapter<PesanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item_pemesanan, parent, false)
        return ViewHolder(itemView)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaHotel: TextView = itemView.findViewById(R.id.namaHotel)
        val alamatHotel: TextView = itemView.findViewById(R.id.alamatHotel)
        val tipeKamar: TextView = itemView.findViewById(R.id.tipeKamar)
        val hargaKamar: TextView = itemView.findViewById(R.id.hargaKamar)
        val namaPemesan: TextView = itemView.findViewById(R.id.namaPemesan)
        val emailPemesan: TextView = itemView.findViewById(R.id.emailPemesan)
        val nohpPemesan: TextView = itemView.findViewById(R.id.nohpPemesan)
        val umurPemesan: TextView = itemView.findViewById(R.id.umurPemesan)
        val totalPembayaran: TextView = itemView.findViewById(R.id.totalPembayaran)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pesan = pemesanList[position]
        holder.namaHotel.text = pesan.namaHotel
        holder.alamatHotel.text = pesan.alamatHotel
        holder.tipeKamar.text = pesan.tipeKamar
        holder.hargaKamar.text = pesan.hargaKamar.toString()
        holder.namaPemesan.text = pesan.namaPemesan
        holder.emailPemesan.text = pesan.emailPemesan
        holder.nohpPemesan.text = pesan.nohpPemesan
        holder.umurPemesan.text = pesan.umurPemesan.toString()
        holder.totalPembayaran.text = pesan.totalPembayaran.toString()
    }

    override fun getItemCount(): Int {
        return pemesanList.size
    }
}