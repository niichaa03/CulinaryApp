package com.niichaa.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CulinaryResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("summary")
    val summary: String,

    @field:SerializedName("sourceUrl")
    val sourceUrl: String,

    @field:SerializedName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String

)
