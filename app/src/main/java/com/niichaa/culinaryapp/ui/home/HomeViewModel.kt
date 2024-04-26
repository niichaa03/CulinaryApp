package com.niichaa.culinaryapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.niichaa.core.domain.usecase.CulinaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(culinaryUseCase: CulinaryUseCase): ViewModel() {

    val culinary = culinaryUseCase.getAllCulinary().asLiveData()

}