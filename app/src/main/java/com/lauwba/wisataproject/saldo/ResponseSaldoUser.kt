package com.lauwba.wisataproject.saldo

import com.google.gson.annotations.SerializedName

data class ResponseSaldoUser(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("saldo")
	val saldo: List<SaldoItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class SaldoItem(

	@field:SerializedName("saldo")
	val saldo: String? = null
)
