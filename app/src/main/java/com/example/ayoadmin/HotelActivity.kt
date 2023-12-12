package com.example.ayoadmin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                intent.putExtra("hotelname", hotelList[position].namahotel)
                startActivity(intent)
            }
        })
    }

    private fun getData() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Hotel")

        myRef.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                hotelList.clear()
                for (HotelSnapshot in dataSnapshot.children){
                    val hotel = HotelSnapshot.getValue(Hotel::class.java)
                    if (hotel != null) {
                        hotelList.add(hotel)
                    }
                }
                hotelAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@HotelActivity, "Gagal menampilkan database: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}