package com.app.petproject.entiti.info

import com.google.gson.annotations.SerializedName


data class Country (

	@SerializedName("iso_3166_1") val iso_3166_1 : String,
	@SerializedName("name") val name : String
)