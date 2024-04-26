package com.niichaa.core.utils

import com.niichaa.core.data.source.local.entity.CulinaryEntity
import com.niichaa.core.data.source.remote.response.CulinaryResponse
import com.niichaa.core.domain.model.Culinary

object DataMapper {

    fun mapResponseToEntities(input: List<CulinaryResponse>): List<CulinaryEntity>{
        val culinaryList = ArrayList<CulinaryEntity>()
        input.map {
            val culinary = CulinaryEntity(
                culinaryId = it.id,
                title = it.title,
                image = it.image,
                summary = it.summary,
                sourceUrl = it.sourceUrl,
                spoonacularSourceUrl = it.spoonacularSourceUrl,
                isFavorite = false
            )
            culinaryList.add(culinary)
        }
        return culinaryList
    }

    fun mapEntitiesToDomain(input: List<CulinaryEntity>): List<Culinary> =
        input.map {
            Culinary(
                culinaryId = it.culinaryId,
                title = it.title,
                image = it.image,
                summary = it.summary,
                sourceUrl = it.sourceUrl,
                spoonacularSourceUrl = it.spoonacularSourceUrl,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Culinary) = CulinaryEntity(
        culinaryId = input.culinaryId,
        title = input.title,
        image = input.image,
        summary = input.summary,
        sourceUrl = input.sourceUrl,
        spoonacularSourceUrl = input.spoonacularSourceUrl,
        isFavorite = input.isFavorite
    )

}