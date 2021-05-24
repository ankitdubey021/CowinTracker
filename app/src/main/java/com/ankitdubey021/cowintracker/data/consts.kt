package com.ankitdubey021.cowintracker.data

import com.google.gson.annotations.SerializedName


data class BlogNetworkEntity(
    @SerializedName("pk")
    val id: Int,
    val title : String,
    val body : String,
    val image : String,
    val category : String
)