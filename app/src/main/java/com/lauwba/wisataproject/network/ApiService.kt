package com.lauwba.wisataproject.network

import com.lauwba.wisataproject.admin.ResponseRequestSaldo
import com.lauwba.wisataproject.datamodel.listbank.ResponseListBank
import com.lauwba.wisataproject.datamodel.mutasi.ResponseMutasi
import com.lauwba.wisataproject.datamodel.transaksisaldo.ResponseTransaksiSaldo
import com.lauwba.wisataproject.datamodel.user.ResponseUser
import com.lauwba.wisataproject.saldo.ResponseSaldo
import com.lauwba.wisataproject.saldo.ResponseSaldoUser
import com.lauwba.wisataproject.service.NotificationHandler
import com.lauwba.wisataproject.wisata.ResponseUpload
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface ApiService {

    //function in controller "list_bank" dontroller in Moota
    @GET("Moota/list_bank/{key}")
    fun getListBank(@Path("key") key: String): retrofit2.Call<ResponseListBank>


    @GET("Moota/mutasi/{key}/{bankId}/{count}")
    fun getMutasi(
        @Path("key") key: String,
        @Path("bankId") bankId: String?,
        @Path("count") count: Int
    ): retrofit2.Call<List<ResponseMutasi?>>   //

    @GET("Moota/getsaldo/{key}")
    fun getsaldo(
        @Path("key") key: String
    ): retrofit2.Call<ResponseSaldo>

    @GET("tampilHistory/{notelp}/{page}")
    fun getTransaksiSaldo(
        @Path("notelp") notelp: String,
        @Path("page") page: Int = 10
    ): retrofit2.Call<ResponseTransaksiSaldo>

    @GET("Moota/getsaldouser/{notelp}")
    fun getSaldoUser(
        @Path("notelp") notelp: String
    ): retrofit2.Call<ResponseSaldoUser>


    //Multipart harus POST gak bisa pake GET
    @Multipart
    @POST("Wisata/addWisata")
    fun doUpload(
        @Part foto: MultipartBody.Part,
        @Part("namawisata") namawisata: RequestBody,
        @Part("deskripsi") deskripsi: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("latitude") lat: RequestBody,
        @Part("longitude") lon: RequestBody,
        @Part("notelp") notelp: RequestBody
    ): retrofit2.Call<ResponseUpload>

    @FormUrlEncoded
    @POST("Register/doRegister")
    fun register(
        @Field("notelp") noTelp: String?,
        @Field("nama") nama: String?,
        @Field("alamat") alamat: String?,
        @Field("email") email: String?
    ): retrofit2.Call<ResponseUpload>

    @FormUrlEncoded
    @POST("Register/getDetail")
    fun doLogin(
        @Field("notelp") noTelp: String
    ): retrofit2.Call<ResponseUser>

    @FormUrlEncoded
    @POST("Moota/requestSaldo")
    fun doRequest(
        @Field("email") email: String?,
        @Field("nominal") nominal: Int?,
        @Field("kode-unik") kodeUnik: Int?
    ): retrofit2.Call<ResponseUpload>


    //firebase
    //key dari cloud messaging firebase
    @Headers(
        "Content-Type:Application/json",
        "Authorization:key=AAAAVPOm88w:APA91bGcZYvq9TkCmMvjQEB9SNockEeuaHfGJMQdYD411vq7RIz32OUkZIh4f-lyn1-ySIPwHOaavPpFcicvieUNDdzsA2vwxQ5Eq7opjJn5y4u71oUlAh6Zz0-Mh2YVMJqgIVpBhPYl"
    )

    @POST("fcm/send")
    fun sendANotification(@Body notificationFcmModel: NotificationHandler.NotificationFcmModel): retrofit2.Call<ResponseBody>

    @GET("Moota/getListSaldoRequest")
    fun requestSaldoList(): retrofit2.Call<ResponseRequestSaldo>

}

