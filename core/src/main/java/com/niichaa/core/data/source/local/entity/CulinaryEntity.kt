package com.niichaa.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "culinary")
data class CulinaryEntity(
    @PrimaryKey
    @ColumnInfo(name = "culinaryId")
    val culinaryId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "summary")
    val summary: String,

    @ColumnInfo(name = "sourceUrl")
    val sourceUrl: String,

    @ColumnInfo(name = "spoonacularSourceUrl")
    val spoonacularSourceUrl: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
