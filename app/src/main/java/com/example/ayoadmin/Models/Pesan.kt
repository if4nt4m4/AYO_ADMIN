package com.example.ayoadmin.Models

class Pesan (
    var namaHotel: String? = null,
    var alamatHotel: String? = null,
    var tipeKamar: String? = null,
    var hargaKamar: Double? = null,
    val tglCheckin: String?=null,
    val tglCheckout: String?= null,
    var namaPemesan: String? = null,
    var emailPemesan: String? = null,
    var nohpPemesan: String? = null,
    var umurPemesan: Int? = null,
    var totalPembayaran: Double? = null
)