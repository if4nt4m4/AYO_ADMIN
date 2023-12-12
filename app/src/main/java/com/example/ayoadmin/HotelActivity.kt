package com.example.ayoadmin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Adapter.HotelAdapter
import com.example.ayoadmin.Models.Hotel
import com.example.ayoadmin.Models.Pesan
import com.example.ayoadmin.databinding.ActivityHotelBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HotelActivity : AppCompatActivity() {

    private lateinit var hotelList: ArrayList<Hotel>
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityHotelBinding
    private lateinit var hotelAdapter: HotelAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hotelList = ArrayList()

        binding.ivAdd.setOnClickListener {
            val intent = Intent(this, AddHotelActivity::class.java)
            startActivity(intent)
        }

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.rv_listhotel)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        hotelAdapter = HotelAdapter(this, hotelList)
        recyclerView.adapter = hotelAdapter

        getData()

        hotelAdapter.setOnItemClickListener(object : HotelAdapter.OnItemClickListener{
            override fun onItemClick(position: Int){
                val intent = Intent(this@HotelActivity, PemesananActivity::class.java)
                intent.putExtra("hotelname", hotelList[position].itemName)
                startActivity(intent)
            }
        })
    }

    private fun getData() {
        val ref = FirebaseDatabase.getInstance().getReference("Hotel")
        ref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val newList = dataSnapshot.children.mapNotNull{it.getValue(Hotel::class.java)}
                    hotelList.clear()
                    hotelList.addAll(newList)
                    hotelAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("Database Error: ", databaseError.getMessage())
            }
        })
    }
}