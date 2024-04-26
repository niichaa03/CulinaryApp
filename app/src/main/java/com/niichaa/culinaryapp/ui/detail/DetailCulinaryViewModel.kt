package com.niichaa.culinaryapp.ui.detail

import androidx.lifecycle.ViewModel
import com.niichaa.core.domain.model.Culinary
import com.niichaa.core.domain.usecase.CulinaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCulinaryViewModel @Inject constructor(private val culinaryUseCase: CulinaryUseCase) : ViewModel() {

    fun setFavoriteCulinary(culinary: Culinary, newStatus: Boolean) =
        culinaryUseCase.setFavoriteCulinary(culinary, newStatus)

}