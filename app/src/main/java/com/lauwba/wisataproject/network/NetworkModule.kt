package com.lauwba.wisataproject.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule {

    //    const val BASE_URL = "http://192.168.18.8/CodeIgniter/WisataProjectWeb/index.php/"
    const val BASE_URL = "http://192.168.18.38/wisata/index.php/"
    const val FIREBASE_URL = "https://fcm.googleapis.com/"

//    const val id="id"

    const val url_gambar_berita = BASE_URL + "assets/upload_berita/"
    val url_detail_berita = BASE_URL + "index.php/Webservice/select_by_get_berita/"
    val url_berita = BASE_URL + "index.php/Webservice/select_berita/"



    fun getOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    fun getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

    fun getRetrofitFCM(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FIREBASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    fun getFcmService(): ApiService {
        return getRetrofitFCM().create(ApiService::class.java)
    }

    private const val Host = "http://192.168.18.38/wisata/"
    const val id = "id"
    const val url_gambar_category = Host + "asset/wisata/"
}