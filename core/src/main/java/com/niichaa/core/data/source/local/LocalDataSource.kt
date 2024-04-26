package com.niichaa.core.data.source.local

import com.niichaa.core.data.source.local.entity.CulinaryEntity
import com.niichaa.core.data.source.local.room.CulinaryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val culinaryDao: CulinaryDao) {

    fun getAllCulinary(): Flow<List<CulinaryEntity>> = culinaryDao.getAllCulinary()

    fun getFavoriteCulinary(): Flow<List<CulinaryEntity>> = culinaryDao.getFavoriteCulinary()

    suspend fun insertCulinary(culinaryList: List<CulinaryEntity>) = culinaryDao.insertCulinary(culinaryList)

    fun setFavoriteCulinary(culinary: CulinaryEntity, newState: Boolean){
        culinary.isFavorite = newState
        culinaryDao.updateFavoriteCulinary(culinary)
    }

}

