package com.niichaa.core.domain.usecase

import com.niichaa.core.data.Resource
import com.niichaa.core.domain.model.Culinary
import kotlinx.coroutines.flow.Flow

interface CulinaryUseCase {
    fun getAllCulinary(): Flow<Resource<List<Culinary>>>

    fun getFavoriteCulinary(): Flow<List<Culinary>>

    fun setFavoriteCulinary(culinary: Culinary, state: Boolean)
}