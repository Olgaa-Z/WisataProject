package com.lauwba.wisataproject

object Config {

    private const val Host = "http://192.168.18.38/wisata/"
    const val id = "id"
    const val url_gambar_gallery = Host + "asset/wisata/"
    val url_gallery = "http://192.168.18.38/wisata/index.php/ApiService/select_foto/"
//    val url_gallery = Host+"index.php/ApiServive/select_gallery/"

    val URL_CARI_WISATA = Host + "index.php/Wisata/cari_wisata?s="
    val URL_GAMBAR = Host + "asset/wisata/"
    val ID_WISATA = "id_wisata"
    val URL_WISATA = "http://192.168.18.38/wisata/index.php/Wisata"
    val DATA_ARRAY = "data"
}