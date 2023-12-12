package com.example.ayoadmin

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ayoadmin.Item.itemDs
import com.example.ayoadmin.databinding.ActivityAddHotelBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.lang.ref.Reference
import java.util.Base64
import java.util.UUID

class AddHotelActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference
    private lateinit var binding: ActivityAddHotelBinding
    var sImage:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btClear.setOnClickListener {
            binding.ivAddimage.setImageResource(android.R.color.transparent)
            binding.etNamahotel.setText("")
            binding.etAlamathotel.setText("")
            binding.etWisata.setText("")
            binding.etFasilitas.setText("")
            binding.etTipekamar1.setText("")
            binding.etHargatipekamar1.setText("")
            binding.etTipekamar2.setText("")
            binding.etHargatipekamar2.setText("")
            binding.etTipekamar3.setText("")
            binding.etHargatipekamar3.setText("")
        }
    }
    fun insertData(view: View){
        val itemName = binding.etNamahotel.text.toString()
        val itemAddress = binding.etAlamathotel.text.toString()
        val itemWis = binding.etWisata.text.toString()
        val itemFasilitas = binding.etFasilitas.text.toString()
        val itemTK1 = binding.etTipekamar1.text.toString()
        val itemHTK1 = binding.etHargatipekamar1.text.toString().toInt()
        val itemTK2 = binding.etTipekamar2.text.toString()
        val itemHTK2 = binding.etHargatipekamar2.text.toString().toInt()
        val itemTK3 = binding.etTipekamar3.text.toString()
        val itemHTK3 = binding.etHargatipekamar3.text.toString().toInt()
        db = FirebaseDatabase.getInstance().getReference("Hotel")
        val item = itemDs(sImage, itemName, itemAddress, itemFasilitas, itemWis, itemTK1, itemHTK1, itemTK2, itemHTK2, itemTK3, itemHTK3 )
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key
        db.child(id.toString()).setValue(item).addOnSuccessListener {
            sImage = ""
            binding.etNamahotel.text.clear()
            binding.etAlamathotel.text.clear()
            binding.etWisata.text.clear()
            binding.etFasilitas.text.clear()
            binding.etTipekamar1.text.clear()
            binding.etHargatipekamar1.text.clear()
            binding.etTipekamar2.text.clear()
            binding.etHargatipekamar2.text.clear()
            binding.etTipekamar3.text.clear()
            binding.etHargatipekamar3.text.clear()
            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show()
        }
    }

    fun insertImage(view: View){
        var myfileintent = Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher.launch(myfileintent)
    }
    private val ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult ->
        if (result.resultCode== RESULT_OK){
            val uri = result.data!!.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val myBitmap = BitmapFactory.decodeStream(inputStream)
                val stream =  ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val bytes = stream.toByteArray()
                sImage = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
                binding.ivAddimage.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }

    }
}