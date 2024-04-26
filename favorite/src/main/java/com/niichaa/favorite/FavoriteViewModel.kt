package com.niichaa.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.niichaa.core.domain.usecase.CulinaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(culinaryUseCase: CulinaryUseCase): ViewModel() {

    val favoriteCulinary = culinaryUseCase.getFavoriteCulinary().asLiveData()

}