package com.niichaa.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListCulinaryResponse(
    @field:SerializedName("results")
    val results: List<CulinaryResponse>
)
