package com.niichaa.culinaryapp.di

import com.niichaa.core.domain.usecase.CulinaryUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteDependencies {

    fun culinaryUseCase(): CulinaryUseCase

}