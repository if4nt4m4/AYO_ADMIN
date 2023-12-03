package com.example.ayoadmin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Adapter.PesanAdapter
import com.example.ayoadmin.Models.Pesan
import com.example.ayoadmin.databinding.ActivityPemesananBinding
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*

class PemesananActivity : AppCompatActivity() {

    private lateinit var pemesanList: ArrayList<Pesan>
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var pesanAdapter: PesanAdapter
    private lateinit var binding: ActivityPemesananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemesanan)

        binding.ivBack.setOnClickListener{
            val intent = Intent(this, HotelActivity::class.java)
            startActivity(intent)
        }

        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.rv_pemesanan)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        pesanAdapter = PesanAdapter(this, pemesanList)
        recyclerView.adapter = pesanAdapter

        getData()


        val NamaHotel = findViewById<TextView>(R.id.tv_nmhotel)
        val hotelName = intent.getStringExtra("hotelname")
        NamaHotel.text = hotelName
    }

    private fun getData(){
        val intent = intent
        val namaHotel = intent.getStringExtra("hotelname")

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Pemesanan")

        myRef.addValueEventListener(object : ValueEventListener{
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                pemesanList.clear()
                for (PemesanaSnapshot in dataSnapshot.children){
                    val pemesan = PemesanaSnapshot.getValue(Pesan::class.java)
                    if (pemesan != null && pemesan.namaHotel == namaHotel){
                        pemesanList.add(pemesan)
                    }
                }
                pesanAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@PemesananActivity, "Gagal menampilkan database: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}