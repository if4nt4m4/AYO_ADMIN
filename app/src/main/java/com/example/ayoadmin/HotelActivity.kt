package com.example.ayoadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Adapter.HotelAdapter
import com.example.ayoadmin.Models.Hotel
import com.google.firebase.database.DatabaseReference

class HotelActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        recyclerView = findViewById(R.id.rv_listhotel)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val hotelList = createHotelList()
        val adapter = HotelAdapter(hotelList)
        recyclerView.adapter = adapter
    }
    private fun createHotelList(): MutableList<Hotel>{
        val hotelList = mutableListOf<Hotel>()

        val hotel1 = Hotel("AYO MADIUN",)
        hotelList.add(hotel1)

        val hotel2 = Hotel("AYO MADIUN",)
        hotelList.add(hotel2)

        return hotelList
    }
}