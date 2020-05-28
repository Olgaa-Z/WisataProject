package com.lauwba.wisataproject.datamodel.mutasi

import com.google.gson.annotations.SerializedName

data class ResponseMutasi (
    @SerializedName("account_number") val account_number : String,
    @SerializedName("date") val date : String,
    @SerializedName("description") val description : String,
    @SerializedName("amount") val amount : Int,
    @SerializedName("type") val type : String,
    @SerializedName("note") val note : String,
    @SerializedName("balance") val balance : Int,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("mutation_id") val mutation_id : String,
    @SerializedName("token") val token : String,
    @SerializedName("bank_id") val bank_id : String,
    @SerializedName("bank") val bank : Bank
)