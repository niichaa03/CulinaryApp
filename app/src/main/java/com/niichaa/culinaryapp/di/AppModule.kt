package com.niichaa.culinaryapp.di

import com.niichaa.core.data.CulinaryRepository
import com.niichaa.core.domain.usecase.CulinaryInteractor
import com.niichaa.core.domain.usecase.CulinaryUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideCulinaryUseCase(culinaryRepository: CulinaryRepository): CulinaryUseCase = CulinaryInteractor(culinaryRepository)

}