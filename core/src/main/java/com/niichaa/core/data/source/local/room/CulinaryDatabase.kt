package com.niichaa.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niichaa.core.data.source.local.entity.CulinaryEntity

@Database(entities = [CulinaryEntity::class], version = 1, exportSchema = false)
abstract class CulinaryDatabase : RoomDatabase() {
    abstract fun culinaryDao(): CulinaryDao
}