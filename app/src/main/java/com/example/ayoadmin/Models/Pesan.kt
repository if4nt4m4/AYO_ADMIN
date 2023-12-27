package com.example.ayoadmin.Models

data class Pesan(
    val namaHotel: String? = null,
    val alamat: String? = null,
    val tipeKamar: String? = null,
    val hargaKamar: Int = 0,
    val tanggalCheckIn: String? = null,
    val tanggalCheckOut: String? = null,
    val namaPemesan: String? = null,
    val email: String? = null,
    val noHp: String? = null,
    val umur: Int ? = null,
    val jumlahPembayaran: Int? = null,
    val kodePembayaran: String? = null
)
