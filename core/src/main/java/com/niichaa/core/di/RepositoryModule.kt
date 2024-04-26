package com.niichaa.core.di

import com.niichaa.core.data.CulinaryRepository
import com.niichaa.core.domain.repository.ICulinaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(culinaryRepository: CulinaryRepository): ICulinaryRepository

}