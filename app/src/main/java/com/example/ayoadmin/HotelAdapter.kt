package com.example.ayoadmin

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(private val hotelList: MutableList<Hotel>): RecyclerView.Adapter<HotelAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_hotel, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addHotel(hotel: Hotel){
        hotelList.add(hotel)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel = hotelList[position]
        holder.tvNamaHotel.text = hotel.namahotel
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNamaHotel: TextView = itemView.findViewById(R.id.tv_namahotel)
    }
}