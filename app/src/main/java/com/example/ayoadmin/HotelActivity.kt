package com.example.ayoadmin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Adapter.HotelAdapter
import com.example.ayoadmin.Models.Hotel
import com.example.ayoadmin.databinding.ActivityHotelBinding

class HotelActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityHotelBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val list = ArrayList<Hotel>()
        list.add(Hotel("AYO MADIUN"))
        list.add(Hotel("AYO NGAWI"))
        list.add(Hotel("AYO MAGETAN"))

        val adapter = HotelAdapter(list)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : HotelAdapter.OnItemClickListener{
            override fun onItemClick(position: Int){
                val intent = Intent(this@HotelActivity, PemesananActivity::class.java)
                intent.putExtra("hotelname", list[position].namahotel)
                startActivity(intent)
            }
        })
    }
}