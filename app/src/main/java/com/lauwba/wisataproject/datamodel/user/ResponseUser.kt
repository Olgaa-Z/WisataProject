package com.lauwba.wisataproject.datamodel.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseUser(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: List<DataItemUser?>? = null,

    @field:SerializedName("message")
    val message: String? = null
) : Serializable

data class DataItemUser(

    @field:SerializedName("notelp")
    val notelp: String? = null,

    @field:SerializedName("nama")
    val nama: String? = null,

    @field:SerializedName("terdaftar_pada")
    val terdaftarPada: String? = null,

    @field:SerializedName("saldo")
    val saldo: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("alamat")
    val alamat: String? = null
) : Serializable