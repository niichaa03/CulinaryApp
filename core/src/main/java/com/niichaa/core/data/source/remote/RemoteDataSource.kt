package com.niichaa.core.data.source.remote

import android.util.Log
import com.niichaa.core.data.source.remote.network.ApiResponse
import com.niichaa.core.data.source.remote.network.ApiService
import com.niichaa.core.data.source.remote.response.CulinaryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){

    suspend fun getAllCulinary(): Flow<ApiResponse<List<CulinaryResponse>>>{
        return flow {
            try {
                val response = apiService.getAllCulinary()
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}