package com.lauwba.wisataproject.saldo

import com.google.gson.annotations.SerializedName

data class ResponseSaldo(

	@field:SerializedName("balance")
	val balance: Int? = null,

	@field:SerializedName("bill")
	val bill: Int? = null
)
