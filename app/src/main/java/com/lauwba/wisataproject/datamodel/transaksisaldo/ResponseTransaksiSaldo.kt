package com.lauwba.wisataproject.datamodel.transaksisaldo

import com.google.gson.annotations.SerializedName

data class ResponseTransaksiSaldo(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("history")
	val history: List<HistoryItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class HistoryItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("balance")
	val balance: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)
