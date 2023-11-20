package com.example.ayoadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import androidx.recyclerview.widget.RecyclerView

class HotelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_listhotel)

        val hotelList = createHotelList()
        val adapter = HotelAdapter(hotelList)
        recyclerView.adapter = adapter
    }

    private fun createHotelList(): MutableList<Hotel>{
        val hotelList = mutableListOf<Hotel>()

        val hotel1 = Hotel(R.drawable.logo_admin,"AYO MADIUN", "Kota Madiun")
        hotelList.add(hotel1)

        val hotel2 = Hotel(R.drawable.logo_admin,"AYO MADIUN", "Kota Madiun")
        hotelList.add(hotel2)

        val hotel3 = Hotel(R.drawable.logo_admin,"AYO MADIUN", "Kota Madiun")
        hotelList.add(hotel3)

        return hotelList
    }
}