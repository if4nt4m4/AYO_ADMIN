package com.example.ayoadmin.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Models.Hotel
import com.example.ayoadmin.R

class HotelAdapter(private val list: MutableList<Hotel>): RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item_hotel, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addHotel(hotel: Hotel){
        list.add(hotel)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel = list[position]
        holder.tvNamaHotel.text = hotel.namahotel
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNamaHotel: TextView = itemView.findViewById(R.id.tv_namahotel)

        init {
            itemView.setOnClickListener{
                onItemClickListener?.onItemClick(adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}