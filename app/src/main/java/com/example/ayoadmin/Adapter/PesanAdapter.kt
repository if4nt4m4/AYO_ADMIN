package com.example.ayoadmin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Models.Pesan
import com.example.ayoadmin.R
import com.google.firebase.database.FirebaseDatabase

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
        val tglCheckin: TextView = itemView.findViewById(R.id.checkIn)
        val tglCheckout: TextView = itemView.findViewById(R.id.checkOut)
        val namaPemesan: TextView = itemView.findViewById(R.id.namaPemesan)
        val emailPemesan: TextView = itemView.findViewById(R.id.emailPemesan)
        val nohpPemesan: TextView = itemView.findViewById(R.id.nohpPemesan)
        val umurPemesan: TextView = itemView.findViewById(R.id.umurPemesan)
        val totalPembayaran: TextView = itemView.findViewById(R.id.totalPembayaran)
        val kodePembayaran: TextView = itemView.findViewById(R.id.kodePembayaran)
        val btCheckin: Button = itemView.findViewById(R.id.bt_checkin)
        val btCheckout: Button = itemView.findViewById(R.id.bt_checkout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pesan = pemesanList[position]
        holder.namaHotel.text = pesan.namaHotel
        holder.alamatHotel.text = pesan.alamat
        holder.tipeKamar.text = pesan.tipeKamar
        holder.hargaKamar.text = pesan.hargaKamar.toString()
        holder.tglCheckin.text = pesan.tanggalCheckIn
        holder.tglCheckout.text = pesan.tanggalCheckOut
        holder.namaPemesan.text = pesan.namaPemesan
        holder.emailPemesan.text = pesan.email
        holder.nohpPemesan.text = pesan.noHp?.toString() ?: ""
        holder.umurPemesan.text = pesan.umur?.toString() ?: ""
        holder.totalPembayaran.text = pesan.jumlahPembayaran?.toString() ?: ""
        holder.kodePembayaran.text = pesan.kodePembayaran?.toString() ?: ""

        holder.btCheckin.setOnClickListener{
            val database = FirebaseDatabase.getInstance()
            val checkInRef = database.getReference("Pemesanan")
            checkInRef.push().setValue("Check In")
            Toast.makeText(context, "Tamu telah Check In", Toast.LENGTH_SHORT).show()
        }
        holder.btCheckout.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val checkOutRef = database.getReference("Pemesanan")
            checkOutRef.push().setValue("Check Out")
            Toast.makeText(context, "Tamu telah Check Out", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return pemesanList.size
    }
}