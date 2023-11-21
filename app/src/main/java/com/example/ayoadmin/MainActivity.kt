package com.example.ayoadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonManage = findViewById<Button>(R.id.bt_manage)

        buttonManage.setOnClickListener{
            val intent = Intent(this, HotelActivity::class.java)
            startActivity(intent)
        }
    }
}