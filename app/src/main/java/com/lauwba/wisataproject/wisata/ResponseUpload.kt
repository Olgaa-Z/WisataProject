package com.lauwba.wisataproject.wisata

import com.google.gson.annotations.SerializedName

data class ResponseUpload (

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
)