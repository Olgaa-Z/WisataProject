package com.lauwba.wisataproject.datamodel.mutasi

import com.google.gson.annotations.SerializedName


data class Bank (
        @SerializedName("corporate_id") val corporate_id : String,
        @SerializedName("username") val username : String,
        @SerializedName("atas_nama") val atas_nama : String,
        @SerializedName("balance") val balance : Double,
        @SerializedName("account_number") val account_number : String,
        @SerializedName("bank_type") val bank_type : String,
        @SerializedName("login_retry") val login_retry : Int,
        @SerializedName("date_from") val date_from : String,
        @SerializedName("date_to") val date_to : String,
        @SerializedName("interval_refresh") val interval_refresh : Int,
        @SerializedName("is_active") val is_active : Boolean,
        @SerializedName("in_queue") val in_queue : Int,
        @SerializedName("in_progress") val in_progress : Int,
        @SerializedName("recurred_at") val recurred_at : String,
        @SerializedName("created_at") val created_at : String,
        @SerializedName("token") val token : String,
        @SerializedName("bank_id") val bank_id : String,
        @SerializedName("label") val label : String,
        @SerializedName("last_update") val last_update : String
    )
