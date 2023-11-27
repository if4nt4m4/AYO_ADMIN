package com.example.ayoadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_listhotel)
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