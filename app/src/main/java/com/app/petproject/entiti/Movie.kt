package com.app.petproject.entiti

import com.google.gson.annotations.SerializedName


data class Movie(

    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Results>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)