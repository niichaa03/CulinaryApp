package com.niichaa.core.domain.usecase

import com.niichaa.core.data.Resource
import com.niichaa.core.domain.model.Culinary
import com.niichaa.core.domain.repository.ICulinaryRepository
import kotlinx.coroutines.flow.Flow

class CulinaryInteractor(private val culinaryRepository: ICulinaryRepository): CulinaryUseCase {
    override fun getAllCulinary() = culinaryRepository.getAllCulinary()

    override fun getFavoriteCulinary() = culinaryRepository.getFavoriteCulinary()

    override fun setFavoriteCulinary(culinary: Culinary, state: Boolean) = culinaryRepository.setFavoriteCulinary(culinary, state)

}