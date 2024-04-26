package com.niichaa.core.data

import com.niichaa.core.data.source.local.LocalDataSource
import com.niichaa.core.data.source.remote.RemoteDataSource
import com.niichaa.core.data.source.remote.network.ApiResponse
import com.niichaa.core.data.source.remote.response.CulinaryResponse
import com.niichaa.core.domain.model.Culinary
import com.niichaa.core.domain.repository.ICulinaryRepository
import com.niichaa.core.utils.AppExecutors
import com.niichaa.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CulinaryRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICulinaryRepository {
    override fun getAllCulinary(): Flow<Resource<List<Culinary>>> =
        object : NetworkBoundResource<List<Culinary>, List<CulinaryResponse>>() {
            override fun loadFromDB(): Flow<List<Culinary>> {
                return localDataSource.getAllCulinary().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Culinary>?): Boolean=
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<CulinaryResponse>>> =
                remoteDataSource.getAllCulinary()

            override suspend fun saveCallResult(data: List<CulinaryResponse>) {
                val culinaryList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertCulinary(culinaryList)
            }
        }.asFlow()

    override fun getFavoriteCulinary(): Flow<List<Culinary>> {
        return localDataSource.getFavoriteCulinary().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteCulinary(culinary: Culinary, state: Boolean) {
        val culinaryEntity = DataMapper.mapDomainToEntity(culinary)
        appExecutors.diskIO().execute { localDataSource.setFavoriteCulinary(culinaryEntity, state) }
    }


}