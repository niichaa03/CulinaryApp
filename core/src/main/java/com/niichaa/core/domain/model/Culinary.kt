package com.niichaa.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Culinary(
    val culinaryId: Int,
    val title: String,
    val image: String,
    val summary: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    var isFavorite: Boolean = false
): Parcelable
