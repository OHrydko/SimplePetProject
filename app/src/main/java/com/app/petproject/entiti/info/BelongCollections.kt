package com.app.petproject.entiti.info

import com.google.gson.annotations.SerializedName

data class BelongCollections(

	@SerializedName("id") val id: Int,
	@SerializedName("name") val name: String,
	@SerializedName("poster_path") val poster_path: String,
	@SerializedName("backdrop_path") val backdrop_path: String
)