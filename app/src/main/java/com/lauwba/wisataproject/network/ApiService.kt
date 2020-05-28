package com.lauwba.wisataproject.network

import com.lauwba.wisataproject.datamodel.listbank.ResponseListBank
import com.lauwba.wisataproject.datamodel.mutasi.ResponseMutasi
import com.lauwba.wisataproject.datamodel.transaksisaldo.ResponseTransaksiSaldo
import com.lauwba.wisataproject.saldo.ResponseSaldo
import com.lauwba.wisataproject.saldo.ResponseSaldoUser
import com.lauwba.wisataproject.wisata.ResponseUpload
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.util.*

interface ApiService {

    //function in controller "list_bank" dontroller in Moota
    @GET("Moota/list_bank/{key}")
    fun getListBank(@Path("key") key: String): retrofit2.Call<ResponseListBank>


    @GET("Moota/mutasi/{key}/{bankId}/{count}")
    fun getMutasi(
        @Path("key") key: String,
        @Path("bankId") bankId: String?,
        @Path("count") count:Int
    ): retrofit2.Call<List<ResponseMutasi?>>   //

    @GET("Moota/getsaldo/{key}")
    fun getsaldo(
        @Path("key") key : String): retrofit2.Call<ResponseSaldo>

    @GET("tampilHistory/{notelp}/{page}")
    fun getTransaksiSaldo(
        @Path("notelp") notelp:String,
        @Path("page") page:Int=10
    ): retrofit2.Call<ResponseTransaksiSaldo>

    @GET("Moota/getsaldouser/{notelp}")
    fun getSaldoUser(
        @Path("notelp") notelp:String
    ): retrofit2.Call<ResponseSaldoUser>


    //Multipart harus POST gak bisa pake GET
    @Multipart
    @POST("Wisata/addWisata")
    fun doUpload(
        @Part foto : MultipartBody.Part,
        @Part ("nama_wisata") namawisata: RequestBody,
        @Part ("deskripsi") deskripsi: RequestBody,
        @Part ("alamat") alamat: RequestBody,
        @Part ("latitude") lat: RequestBody,
        @Part ("longitude") lon: RequestBody,
        @Part ("notelp") notelp: RequestBody
        ): retrofit2.Call<ResponseUpload>

}
