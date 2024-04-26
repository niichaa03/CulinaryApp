package com.niichaa.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.niichaa.core.data.source.local.entity.CulinaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CulinaryDao {
    @Query("SELECT * FROM culinary")
    fun getAllCulinary(): Flow<List<CulinaryEntity>>

    @Query("SELECT * FROM culinary where isFavorite = 1")
    fun getFavoriteCulinary(): Flow<List<CulinaryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCulinary(culinary: List<CulinaryEntity>)

    @Update
    fun updateFavoriteCulinary(culinary: CulinaryEntity)
}