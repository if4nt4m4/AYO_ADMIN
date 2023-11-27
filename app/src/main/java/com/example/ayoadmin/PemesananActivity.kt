package com.example.ayoadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ayoadmin.Adapter.PesanAdapter
import com.example.ayoadmin.Models.Pesan
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PemesananActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pesanAdapter: PesanAdapter
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemesanan)

        recyclerView = findViewById(R.id.rv_pemesanan)
        recyclerView.layoutManager = LinearLayoutManager(this)

        databaseReference = FirebaseDatabase.getInstance().reference.child("pemesan")
        val options = FirebaseRecyclerOptions.Builder<Pesan>().setQuery(databaseReference, Pesan::class.java)
            .build()

        pesanAdapter = PesanAdapter(options.snapshots)
        recyclerView.adapter = pesanAdapter
    }

    override fun onStart() {
        super.onStart()
        pesanAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        pesanAdapter.stopListening()
    }

}