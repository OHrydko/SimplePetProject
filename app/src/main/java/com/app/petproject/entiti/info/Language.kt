package com.app.petproject.entiti.info

import com.google.gson.annotations.SerializedName


data class Language (

	@SerializedName("english_name") val english_name : String,
	@SerializedName("iso_639_1") val iso_639_1 : String,
	@SerializedName("name") val name : String
)