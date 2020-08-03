package com.lauwba.wisataproject.content

object ConfigApp {
    private val HOST = "http://192.168.18.38/wisata/"
    val URL_BERITA_ = HOST + "index.php/json"
    val URL_BERITA_DETAIL = HOST + "index.php/json/get_berita/"
    val URL_GAMBAR = HOST + "assets/gambar/"
    val URL_CARI_BERITA = HOST + "index.php/json/cari_berita?s="
    val DATA_ARRAY = "data"
    val ID_BERITA = "id_berita"
}