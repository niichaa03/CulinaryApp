package com.niichaa.core.data.source.remote.network

import com.niichaa.core.data.source.remote.response.ListCulinaryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("complexSearch")
    suspend fun getAllCulinary(
        @Query("apiKey") apiKey: String = "f05545aa7077459fa13558185d8c5966",
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("fillIngredients") fillIngredients: Boolean = true
    ): ListCulinaryResponse

}