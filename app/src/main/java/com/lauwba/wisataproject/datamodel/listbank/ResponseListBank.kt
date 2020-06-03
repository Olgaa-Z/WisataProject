package com.lauwba.wisataproject.datamodel.listbank

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//@Parcelize
data class ResponseListBank(

	@field:SerializedName("first_page_url")
	val firstPageUrl: String? = null,

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItemBank?>? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("last_page_url")
	val lastPageUrl: String? = null,

	@field:SerializedName("next_page_url")
	val nextPageUrl: String? = null,

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("to")
	val to: Int? = null,

	@field:SerializedName("prev_page_url")
	val prevPageUrl: String? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null
) : Serializable

data class DataItemBank(

	@field:SerializedName("account_number")
	val accountNumber: String? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("in_queue")
	val inQueue: Int? = null,

	@field:SerializedName("bank_type")
	val bankType: String? = null,

	@field:SerializedName("login_retry")
	val loginRetry: Int? = null,

	@field:SerializedName("interval_refresh")
	val intervalRefresh: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("date_to")
	val dateTo: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("recurred_at")
	val recurredAt: String? = null,

	@field:SerializedName("in_progress")
	val inProgress: Int? = null,

	@field:SerializedName("balance")
	val balance: String? = null,

	@field:SerializedName("bank_id")
	val bankId: String? = null,

	@field:SerializedName("corporate_id")
	val corporateId: String? = null,

	@field:SerializedName("last_update")
	val lastUpdate: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("atas_nama")
	val atasNama: String? = null,

	@field:SerializedName("date_from")
	val dateFrom: String? = null
) : Serializable