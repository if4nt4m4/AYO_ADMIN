package com.example.ayoadmin

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import com.example.ayoadmin.databinding.ActivityAddHotelBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception
import java.lang.ref.Reference
import java.util.UUID

class AddHotelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddHotelBinding
    var fileUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hotel)

        binding.btPilihgambar.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Choose Image to Upload"),0
            )
        }

        binding.btClear.setOnClickListener {
            binding.ivAddimage.setImageResource(android.R.color.transparent)
            binding.etNamahotel.setText("")
            binding.etAlamathotel.setText("")
            binding.etFasilitas.setText("")
            binding.etTipekamar1.setText("")
            binding.etTipekamar2.setText("")
            binding.etTipekamar3.setText("")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == RESULT_OK && data != null && data.data != null){
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri)
                binding.ivAddimage.setImageBitmap(bitmap)
            }catch (e:Exception){
                Log.e("Exception", "Error"+e)
            }
        }
    }

    fun uploadImage(){
        if (fileUri!=null){
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading Image......")
            progressDialog.setMessage("Processing.....")
            progressDialog.show()

            val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference()
                .child(UUID.randomUUID().toString())
            ref.putFile(fileUri!!).add
        }
    }
}